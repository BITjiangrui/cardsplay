package com.cardsplay.win3cards;

import com.cardsplay.access.api.CardsPlayNodeId;
import com.cardsplay.access.api.CardsPlayServerService;
import com.cardsplay.core.api.ClientResponse;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.api.RoomService;
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
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.UUID;

import static com.cardsplay.core.impl.RoomManager.roomCapacity;

public class WinThreeCardsLoadBalancer implements CardsPlayServerService {

    public  final static Logger log = LoggerFactory
            .getLogger(WinThreeCardsLoadBalancer.class);

    ServiceRegistry serviceMap;
    RoomService roomService;
    TableService tableService;
    PlayerService playerService;
    //CardsPlayController controller;
    // The default capacity of each table
    private static int playersInTableCapacity = 5;



    private static CardsPlayServerService instance = new WinThreeCardsLoadBalancer();


    private  WinThreeCardsLoadBalancer(){

    }

    @Override
    public void activate() {
        serviceMap = ServiceRegistry.getInstance();
        
        roomService = (RoomService) serviceMap.getService(RoomService.class);
        
        tableService = (TableService) serviceMap.getService(TableService.class);
        
        playerService = (PlayerService) serviceMap.getService(PlayerService.class);
        
        // TODO:Init 1 Room for test, will init from DB in future
        RoomId roomId = new RoomId(UUID.randomUUID());

        Room room = new Room(roomId, 1, roomCapacity, DealType.WinThreeCards,Rule.NEWBEE);

        roomService.addRoom(room);

        // Init 250 Tables in one room
        for(int i=1; i<250; i++){
            TableId tableId = new TableId(UUID.randomUUID());
            Dealer dealer = new WinThreeCardsDealer(room.rule);
            Table table = new Table(tableId, i, playersInTableCapacity);
            tableService.addTable(table);
            tableService.setTableState(tableId, TableStatus.Waiting);
            tableService.setDealer(tableId, dealer);
            roomService.addTableToRoom(roomId, tableId);
        }    
        log.info("WinThreeCardsServer Started");
    }

    @Override
    public void deactivate() {
        log.info("WinThreeCardsServer Stopped");
    }

    @Override
    public ClientResponse getPlayerInfo(CardsPlayNodeId nodeId) {
        PlayerId playerId = new PlayerId(nodeId.nodeId());
        ClientResponse response = null;
        try{
            Player player = playerService.getPlayer(playerId);
            response = ClientResponse.respSuccess(player);
        } catch (ServiceException exception){
            response = ClientResponse.respFail(exception.getCode(), exception.getMsg());
        }
        return response;    
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
                        .dealer(tableService.getDealer(tableId))
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
        ClientResponse response = null;
        try{
            roomService.joinRoom(roomId, playerId);
            response = ClientResponse.respSuccess(true);
        } catch (ServiceException exception){
            response = ClientResponse.respFail(exception.getCode(), exception.getMsg());
        }
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
        response = ClientResponse.respFail(ResponseCode.badRequest, "Please exit and rejoin the table");
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
            response = ClientResponse.respFail(ResponseCode.badRequest, "Please exit and rejoin the table");
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
            response = ClientResponse.respFail(ResponseCode.badRequest, "Please exit and rejoin the table");
        }
        return response;
    }


    public static CardsPlayServerService getInstance(){
        return instance;
    }
}
