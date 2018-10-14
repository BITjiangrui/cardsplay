package com.cardsplay.win3cards;

import com.cardsplay.access.api.CardsPlayClientService;
import com.cardsplay.access.api.CardsPlayController;
import com.cardsplay.access.api.CardsPlayNodeId;
import com.cardsplay.access.api.CardsPlayNodeListener;
import com.cardsplay.access.api.CardsPlayServerService;
import com.cardsplay.core.api.ClientResponse;
import com.cardsplay.core.api.PlayerEvent;
import com.cardsplay.core.api.PlayerListener;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.api.TableEvent;
import com.cardsplay.core.api.TableListener;
import com.cardsplay.core.api.TableService;
import com.cardsplay.core.exception.ResponseCode;
import com.cardsplay.core.exception.ServiceException;
import com.cardsplay.core.impl.ServiceRegistry;
import com.cardsplay.core.models.DealType;
import com.cardsplay.core.models.Dealer;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.RoomInfo;
import com.cardsplay.core.models.Rule;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;
import com.cardsplay.core.models.TableInfo;
import com.cardsplay.core.models.TableStatus;
import com.cardsplay.core.models.TokenType;
import com.cardsplay.core.models.TokenWallet;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.cardsplay.core.impl.RoomManager.roomCapacity;

public class WinThreeCardsServer {

    public  final static Logger log = LoggerFactory
            .getLogger(WinThreeCardsServer.class);

    ServiceRegistry serviceMap;
    RoomService roomService;
    TableService tableService;
    PlayerService playerService;
    CardsPlayController controller;
    // The default capacity of each table
    private static int playersInTableCapacity = 5;



    private static WinThreeCardsServer instance = new WinThreeCardsServer();

    private  CardsPlayNodeHandler cardsPlayNodeHandler = new CardsPlayNodeHandler();

    private InnerPlayerEventListener playerEventListener = new InnerPlayerEventListener();

    private InnerTableEventListener tableEventListener = new InnerTableEventListener();


    private  WinThreeCardsServer(){

    }

    public void init() {
        serviceMap = ServiceRegistry.getInstance();
        
        roomService = (RoomService) serviceMap.getService(RoomService.class);
        
        tableService = (TableService) serviceMap.getService(TableService.class);
        
        playerService = (PlayerService) serviceMap.getService(PlayerService.class);
        
        controller = (CardsPlayController) serviceMap.getService(CardsPlayController.class);
        // TODO:Init 1 Room for test, will init from DB in future
        RoomId roomId = new RoomId(UUID.randomUUID());

        Room room = new Room(roomId, 1, roomCapacity, DealType.WinThreeCards,Rule.NEWBEE);

        roomService.addRoom(room);

        // Init 250 Tables in one room
        for(int i=1; i<250; i++){
            TableId tableId = new TableId(UUID.randomUUID());
            Dealer dealer = new WinThreeCardsDealer(room.rule);
            Table table = new Table(tableId, i, playersInTableCapacity);
            tableService.setDealer(tableId, dealer);
            tableService.addTable(table);
            tableService.setTableState(table.tableId, TableStatus.Waiting);
            roomService.addTableToRoom(roomId, tableId);
        }

        // add listener
        controller.addNodeListener(cardsPlayNodeHandler);
        playerService.addEventListener(playerEventListener);
        tableService.addEventListener(tableEventListener);
    
        log.info("WinThreeCardsServer Started");
    }

    public void stop() {
        log.info("WinThreeCardsServer Stopped");
    }


    public static WinThreeCardsServer getInstance(){
        return instance;
    }

    private class InnerTableEventListener implements TableListener {

        @Override
        public void event(TableEvent event) {
            log.info("{} event happend {}",event.type());
            List<PlayerId> currentPlayers = null;
            List<PlayerId> prevPlayers = null;
            switch (event.type()) {
                // TODO : make sure the relative position is post
                case TABLE_JOIN:
                    currentPlayers = event.subject().playerIds;
                    prevPlayers = event.prevSubject().playerIds;
                    for (PlayerId playerId : currentPlayers){
                        RoomId roomId = roomService.getRoomByTable(event.subject().tableId);
                        CardsPlayClientService client = controller.getCardsPlayClient(new CardsPlayNodeId(playerId.playerId));
                        client.playerJoinIn(roomId, event.prevSubject(), event.subject());
                    }
                    currentPlayers.removeAll(prevPlayers);
                    log.info("Process  TABLE_JOIN event by players {}", currentPlayers);
                    break;
                case TABLE_QUIT:
                    currentPlayers = event.subject().playerIds;
                    prevPlayers = event.prevSubject().playerIds;
                    for (PlayerId playerId : currentPlayers){
                        RoomId roomId = roomService.getRoomByTable(event.prevSubject().tableId);
                        CardsPlayClientService client = controller.getCardsPlayClient(new CardsPlayNodeId(playerId.playerId));
                        client.playerLeave(roomId, event.prevSubject(), event.subject());
                    }
                    prevPlayers.removeAll(currentPlayers);
                    log.info("Process  TABLE_QUIT event by players {}", prevPlayers);
                    break;
                default:
                    break;
            }
        }
    }


    private class InnerPlayerEventListener implements PlayerListener {

        @Override
        public void event(PlayerEvent event) {
            log.info("{} event happend {}",event.type());
            switch (event.type()) {

                case PLAYER_OFFLINE:
                    TableId tableId = tableService.getTableByPlayer(event.subject().playerId).tableId;
                    RoomId roomId = roomService.getRoomByTable(tableId);
                    if(tableService.getTableState(tableId).equals(TableStatus.Waiting)) {
                        tableService.quitTable(tableId,event.subject().playerId);
                        roomService.quitRoom(roomId, event.subject().playerId);
                        playerService.removePlayer(event.subject().playerId);
                    }
                    break;
                case PLAYER_ONLINE:
                    break;

                case PLAYER_READY:
                    Player player = event.subject();
                    Table table = tableService.getTableByPlayer(player.playerId);
                    roomId = roomService.getRoomByTable(table.tableId);
                    for(PlayerId otherPlayerId : playerService.getOtherPlayers(player.playerId)){
                        CardsPlayClientService client = controller.getCardsPlayClient(new CardsPlayNodeId(otherPlayerId.playerId));
                        client.statusChange(roomId, table.tableId, player.playerId, event.prevSubject().state, event.subject().state);
                    }
                    if (tableService.isTableReady(table.tableId)){
                        tableService.getDealer(table.tableId).init();
                    }
                    break;

                case PLAYER_UNDOREADY:
                    player = event.subject();
                    table = tableService.getTableByPlayer(player.playerId);
                    roomId = roomService.getRoomByTable(table.tableId);
                    for(PlayerId otherPlayerId : playerService.getOtherPlayers(player.playerId)){
                        CardsPlayClientService client = controller.getCardsPlayClient(new CardsPlayNodeId(otherPlayerId.playerId));
                        client.statusChange(roomId, table.tableId, player.playerId, event.prevSubject().state, event.subject().state);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public class CardsPlayNodeHandler implements CardsPlayNodeListener{

        @Override
        public void nodeAdded(CardsPlayNodeId nodeId) {
            PlayerId playerId = new PlayerId(nodeId.nodeId());
            // TODO: add Token get in DB to init wallet
            TokenWallet tokenWallet = new TokenWallet(playerId, TokenType.EOS);
            Player player = new Player(playerId, tokenWallet);
            playerService.playerOnline(player);
        }

        @Override
        public void nodeRemoved(CardsPlayNodeId nodeId) {
            PlayerId playerId = new PlayerId(nodeId.nodeId());
            Player player = playerService.getPlayer(playerId);
            playerService.playerOffline(player);
        }
    }
}
