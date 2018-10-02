package com.cardsplay.win3cards;

import com.cardsplay.access.api.CardsPlayController;
import com.cardsplay.access.api.CardsPlayNodeId;
import com.cardsplay.access.api.CardsPlayNodeListener;
import com.cardsplay.access.api.CardsPlayServerService;
import com.cardsplay.core.api.ClientResponse;
import com.cardsplay.core.api.Event;
import com.cardsplay.core.api.EventListener;
import com.cardsplay.core.api.EventSubject;
import com.cardsplay.core.api.PlayerEventSubject;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.api.TableEventSubject;
import com.cardsplay.core.api.TableService;
import com.cardsplay.core.models.DealType;
import com.cardsplay.core.models.Dealer;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;
import com.cardsplay.core.models.TokenType;
import com.cardsplay.core.models.TokenWallet;

import java.util.UUID;

import static com.cardsplay.core.impl.RoomManager.roomCapacity;
import static com.cardsplay.win3cards.Main.serviceMap;
import static com.google.common.base.Preconditions.checkNotNull;

public class WinThreeCardsServer implements CardsPlayServerService {
    RoomService roomService = (RoomService) serviceMap.get(RoomService.class);
    TableService tableService = (TableService) serviceMap.get(TableService.class);
    PlayerService playerService = (PlayerService) serviceMap.get(PlayerService.class);
    CardsPlayController controller = (CardsPlayController) serviceMap.get(CardsPlayController.class);
    // The default capacity of each table
    private static int playersIntaleCapacity = 5;



    private static CardsPlayServerService instance = new WinThreeCardsServer();

    private  CardsPlayNodeHandler cardsPlayNodeHandler = new CardsPlayNodeHandler();

    private InnerPlayerEventListener playerEventListener = new InnerPlayerEventListener();

    private InnerTableEventListener tableEventListener = new InnerTableEventListener();


    private  WinThreeCardsServer(){

    }

    @Override
    public void activate() {
        // Init Room
        RoomId roomId = new RoomId(UUID.randomUUID(), 1);
        Room room = new Room(roomId, roomCapacity, DealType.WinThreeCards);
        roomService.addRoom(room);

        // Init 250 Tables in one room
        for(int i=1; i<250; i++){
            TableId tableId = new TableId(UUID.randomUUID(), i);
            Dealer dealer = new WinThreeCardsDealer();
            Table table = new Table(tableId, dealer, playersIntaleCapacity);
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

    }

    @Override
    public ClientResponse getRooms(CardsPlayNodeId nodeId) {
        Iterable<Room> roomIds = roomService.getRooms();
        ClientResponse response = ClientResponse.respSuccess(roomIds);
        return response;
    }

    @Override
    public ClientResponse joinRoom(CardsPlayNodeId nodeId, RoomId roomId) {
        PlayerId playerId = new PlayerId(nodeId.nodeId());
        Room room = roomService.joinRoom(roomId, playerId);
        ClientResponse response = ClientResponse.respSuccess(room);
        return response;
    }

    @Override
    public ClientResponse joinTable(CardsPlayNodeId nodeId, TableId tableId) {
        PlayerId playerId = new PlayerId(nodeId.nodeId());
        for (Room room : roomService.getRooms()){
            if (room.tableIds.contains(tableId) && room.playerIds.contains(playerId)){
                Table table = tableService.joinTable(tableId, playerId);
                ClientResponse response = ClientResponse.respSuccess(table);
            }
        }
        return null;
    }

    @Override
    public ClientResponse exitRoom(CardsPlayNodeId nodeId, RoomId roomId) {
        return null;
    }

    @Override
    public ClientResponse exitTable(CardsPlayNodeId nodeId, TableId tableId) {
        return null;
    }

    @Override
    public ClientResponse beReady(CardsPlayNodeId nodeId, TableId tableId) {
        return null;
    }

    @Override
    public ClientResponse undoReady(CardsPlayNodeId nodeId, TableId tableId) {
        return null;
    }


    public static CardsPlayServerService getInstance(){
        return instance;
    }

    private class InnerTableEventListener implements EventListener {
        @Override
        public void handle(Event<EventSubject> event) {
            EventSubject subject = null;
            if (event.subject() instanceof PlayerEventSubject) {
                subject = (TableEventSubject) event.subject();
            }
            checkNotNull(subject, "EventSubject is not null");
            switch (event.type()) {
                case TABLE_UPDATE:
                    break;
                default:
                    break;
            }
        }

    }


    private class InnerPlayerEventListener implements EventListener {
        @Override
        public void handle(Event<EventSubject> event) {
            EventSubject subject = null;
            if (event.subject() instanceof PlayerEventSubject) {
                subject = (PlayerEventSubject) event.subject();
            }
            checkNotNull(subject, "EventSubject is not null");
            switch (event.type()) {
                case PLAYER_UPDATE:
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
