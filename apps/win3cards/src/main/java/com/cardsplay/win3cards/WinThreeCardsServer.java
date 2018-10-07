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
import com.cardsplay.util.ResponseCode;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.UUID;

import static com.cardsplay.core.impl.RoomManager.roomCapacity;

public class WinThreeCardsServer implements CardsPlayServerService {

    public  final static Logger log = LoggerFactory
            .getLogger(WinThreeCardsServer.class);

    ServiceRegistry serviceMap = ServiceRegistry.getInstance();
    RoomService roomService = (RoomService) serviceMap.getService(RoomService.class);
    TableService tableService = (TableService) serviceMap.getService(TableService.class);
    PlayerService playerService = (PlayerService) serviceMap.getService(PlayerService.class);
    CardsPlayController controller = (CardsPlayController) serviceMap.getService(CardsPlayController.class);
    // The default capacity of each table
    private static int playersInTableCapacity = 5;



    private static CardsPlayServerService instance = new WinThreeCardsServer();

    private  CardsPlayNodeHandler cardsPlayNodeHandler = new CardsPlayNodeHandler();

    private InnerPlayerEventListener playerEventListener = new InnerPlayerEventListener();

    private InnerTableEventListener tableEventListener = new InnerTableEventListener();


    private  WinThreeCardsServer(){

    }

    @Override
    public void activate() {
        log.info("WinThreeCardsServer Started");
        // Init 1 Room
        RoomId roomId = new RoomId(UUID.randomUUID());
        Room room = new Room(roomId, 1, roomCapacity, DealType.WinThreeCards,Rule.NEWBEE);

        roomService.addRoom(room);

        // Init 250 Tables in one room
        for(int i=1; i<250; i++){
            TableId tableId = new TableId(UUID.randomUUID());
            Dealer dealer = new WinThreeCardsDealer(room.rule);
            Table table = new Table(tableId, i, playersInTableCapacity);
            table.setDealer(dealer);
            tableService.addTable(table);
            roomService.addTableToRoom(roomId, tableId);
        }

        // add listener
        controller.addNodeListener(cardsPlayNodeHandler);
        playerService.addEventListener(playerEventListener);
        tableService.addEventListener(tableEventListener);
    }

    @Override
    public void deactivate() {
        log.info("WinThreeCardsServer Stopped");
    }

    @Override
    public ClientResponse getRoomsInfo() {
        Iterable<Room> roomIds = roomService.getRooms();
        Set roomsInfo = Sets.newConcurrentHashSet();
        for(Room room : roomService.getRooms()){
            Set tablesInfo = Sets.newConcurrentHashSet();
            for(TableId tableId : room.tableIds){
                TableInfo tableInfo = TableInfo.builder().tableId(tableId)
                        .sequence(tableService.getTable(tableId).seq)
                        .players(playerService.getPlayersInTable(tableId))
                        .build();
                tablesInfo.add(tableInfo);
            }

            RoomInfo roomInfo = RoomInfo.builder().roomId(room.roomId)
                    .nickName(room.nickName)
                    .sequence(room.seq)
                    .tablesInfo(tablesInfo)
                    .rule(room.rule)
                    .build();
            roomsInfo.add(roomInfo);
        }
        ClientResponse response = ClientResponse.respSuccess(roomsInfo);
        return response;
    }

    @Override
    public ClientResponse getRoomInfo(RoomId roomId) {
        ClientResponse response = null;
        try {
            Room room = roomService.getRoom(roomId);
            Set tablesInfo = Sets.newConcurrentHashSet();
            for(TableId tableId : room.tableIds){
                TableInfo tableInfo = TableInfo.builder().tableId(tableId)
                        .sequence(tableService.getTable(tableId).seq)
                        .players(playerService.getPlayersInTable(tableId))
                        .build();
                tablesInfo.add(tableInfo);
            }

            RoomInfo roomInfo = RoomInfo.builder().roomId(room.roomId)
                                    .nickName(room.nickName)
                                    .sequence(room.seq)
                                    .tablesInfo(tablesInfo)
                                    .rule(room.rule)
                                    .build();

            response = ClientResponse.respSuccess(roomInfo);
        } catch (ServiceException exception){
            response = ClientResponse.respFail(exception.getCode(), exception.getMsg());
        }
        return response;
    }

    @Override
    public ClientResponse joinRoom(CardsPlayNodeId nodeId, RoomId roomId) {
        PlayerId playerId = new PlayerId(nodeId.nodeId());
        // TODO: add dealer rule check
        roomService.joinRoom(roomId, playerId);
        ClientResponse response = ClientResponse.respSuccess(true);
        return response;
    }

    @Override
    public ClientResponse getTableInfo(TableId tableId) {
        ClientResponse response = null;
        try {
            TableInfo tableInfo = TableInfo.builder().tableId(tableId)
                    .sequence(tableService.getTable(tableId).seq)
                    .players(playerService.getPlayersInTable(tableId))
                    .build();
            response = ClientResponse.respSuccess(tableInfo);
        } catch (ServiceException exception){
            response = ClientResponse.respFail(exception.getCode(), exception.getMsg());
        }
        return response;
    }

    @Override
    public ClientResponse joinTable(CardsPlayNodeId nodeId, TableId tableId) {
        PlayerId playerId = new PlayerId(nodeId.nodeId());
        ClientResponse response = null;
        for (Room room : roomService.getRooms()) {
            if (room.tableIds.contains(tableId) && room.playerIds.contains(playerId)) {
                try {
                    tableService.joinTable(tableId, playerId);
                    playerService.playerUndoReady(playerId);
                    response = ClientResponse.respSuccess(true);
                    return response;
                } catch (ServiceException exception){
                    response = ClientResponse.respFail(exception.getCode(), exception.getMsg());
                    return  response;
                }
            }
        }
        log.error("tableId {} or playerId {} can not be find in all room", tableId, playerId);
        response = ClientResponse.respFail(ResponseCode.badRequest, "请重新加入房间");
        return response;
    }

    @Override
    public ClientResponse exitRoom(CardsPlayNodeId nodeId, RoomId roomId) {
        PlayerId playerId = new PlayerId(nodeId.nodeId());
        Room room =  roomService.getRoom(roomId);
        for(TableId tableId : room.tableIds){
            Table table = tableService.getTable(tableId);
            if(table.playerIds.contains(playerId)){
                tableService.quitTable(tableId, playerId);
            }
        }
        roomService.quitRoom(roomId, playerId);
        ClientResponse response = ClientResponse.respSuccess(true);
        return response;
    }

    @Override
    public ClientResponse exitTable(CardsPlayNodeId nodeId, TableId tableId) {
        PlayerId playerId = new PlayerId(nodeId.nodeId());
        tableService.quitTable(tableId, playerId);
        ClientResponse response = ClientResponse.respSuccess(true);
        return response;
    }

    @Override
    public ClientResponse beReady(CardsPlayNodeId nodeId, TableId tableId) {
        PlayerId playerId = new PlayerId(nodeId.nodeId());
        Table table = tableService.getTable(tableId);
        ClientResponse response = null;
        if(table.playerIds.contains(playerId)){
            try{
                playerService.playerIsReady(playerId);
            } catch (ServiceException exception){
                response = ClientResponse.respFail(exception.getCode(), exception.getMsg());
                return  response;
            }
            response = ClientResponse.respSuccess(true);
        } else {
            log.error("playerId {} can not be find in tableId {}", playerId, tableId);
            response = ClientResponse.respFail(ResponseCode.badRequest, "请退出重新加入牌桌");
        }
        return response;
    }

    @Override
    public ClientResponse undoReady(CardsPlayNodeId nodeId, TableId tableId) {
        PlayerId playerId = new PlayerId(nodeId.nodeId());
        Table table = tableService.getTable(tableId);
        ClientResponse response = null;
        if(table.playerIds.contains(playerId)){
            try{
                playerService.playerUndoReady(playerId);
            } catch (ServiceException exception){
                response = ClientResponse.respFail(exception.getCode(), exception.getMsg());
                return  response;
            }
            response = ClientResponse.respSuccess(true);
        } else {
            log.error("playerId {} can not be find in tableId {}", playerId, tableId);
            response = ClientResponse.respFail(ResponseCode.badRequest, "请退出重新加入牌桌");
        }
        return response;
    }


    public static CardsPlayServerService getInstance(){
        return instance;
    }

    private class InnerTableEventListener implements TableListener {

        @Override
        public void event(TableEvent event) {
            log.info("{} event happend {}",event.type());
            Set<PlayerId> currentPlayers = null;
            Set<PlayerId> prevPlayers = null;
            switch (event.type()) {
                case TABLE_JOIN:
                    currentPlayers = event.subject().playerIds;
                    prevPlayers = event.prevSubject().playerIds;
                    currentPlayers.removeAll(prevPlayers);
                    log.info("Process  TABLE_JOIN event by players {}", currentPlayers);
                    for (PlayerId playerId : currentPlayers){
                        RoomId roomId = roomService.getRoomByTable(event.subject().tableId);
                        for(PlayerId otherPlayerId : playerService.getOtherPlayers(playerId)){
                            CardsPlayClientService client = controller.getCardsPlayClient(new CardsPlayNodeId(otherPlayerId.playerId));
                            client.playerJoinIn(roomId, event.subject().tableId, playerId);
                        }
                    }

                    break;
                case TABLE_QUIT:
                    currentPlayers = event.subject().playerIds;
                    prevPlayers = event.prevSubject().playerIds;
                    prevPlayers.removeAll(currentPlayers);
                    log.info("Process  TABLE_QUIT event by players {}", prevPlayers);
                    for (PlayerId playerId : prevPlayers){
                        RoomId roomId = roomService.getRoomByTable(event.prevSubject().tableId);
                        for(Player otherPlayer : playerService.getPlayersInTable(event.subject().tableId)){
                            CardsPlayClientService client = controller.getCardsPlayClient(new CardsPlayNodeId(otherPlayer.playerId.playerId));
                            client.playerLeave(roomId, event.subject().tableId, playerId);
                        }
                    }
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
                    if(tableService.getTableState(tableId) == TableStatus.Waiting){
                        tableService.quitTable(tableId,event.subject().playerId);
                        roomService.quitRoom(roomId, event.subject().playerId);
                        playerService.removePlayer(event.subject().playerId);
                    }
                    break;
                case PLAYER_ONLINE:
                    tableId = tableService.getTableByPlayer(event.subject().playerId).tableId;
                    roomId = roomService.getRoomByTable(tableId);
                    if(tableService.getTableState(tableId) == TableStatus.Running){
                        // TODO: add offline recover process
                    }
                    break;

                case PLAYER_READY:
                    tableId = tableService.getTableByPlayer(event.subject().playerId).tableId;
                    roomId = roomService.getRoomByTable(tableId);
                    if(tableService.getTableState(tableId) == TableStatus.Running){
                        // TODO: add ready process
                    }
                    break;
                case PLAYER_UNDOREADY:
                    tableId = tableService.getTableByPlayer(event.subject().playerId).tableId;
                    roomId = roomService.getRoomByTable(tableId);
                    if(tableService.getTableState(tableId) == TableStatus.Running){
                        // TODO: add undo ready process
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
