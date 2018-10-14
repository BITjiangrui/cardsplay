package com.cardsplay.core.impl;

import com.cardsplay.core.api.EventListener;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.exception.ResponseCode;
import com.cardsplay.core.exception.ServiceException;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.TableId;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class RoomManager implements RoomService {
    Map<RoomId, Room> roomStore;

    public  final static Logger log = LoggerFactory
            .getLogger(RoomManager.class);
    protected Set<EventListener> eventListener = new CopyOnWriteArraySet<>();

    private static RoomService instance = new RoomManager();
    ServiceRegistry serviceMap;
    PlayerService playerService;
    public static int roomCapacity = 250;

    private RoomManager(){
        roomStore = Maps.newConcurrentMap();
    };
    @Override
    public void activate() {
        serviceMap = ServiceRegistry.getInstance();
        playerService = (PlayerService) serviceMap.getService(PlayerService.class);
        log.info("Room Service Activated");
    }

    @Override
    public void deactivate() {
        log.info("Room Service Deactivated");

    }
    
    public void joinRoom(RoomId roomId, PlayerId player) throws ServiceException{
        Room room = roomStore.get(roomId);
        if (playerService.getPlayer(player).wallet.getAmount() > room.rule.getBalance()){
            room.playerJoinIn(player);
        } else {
            log.error("The balance {} is not enough for player  {} to enter room{}",
                      playerService.getPlayer(player).wallet.getAmount(),player, roomId);
            throw new ServiceException(ResponseCode.badRequest, "balance is not enough");
        }

    }

    public void quitRoom(RoomId room, PlayerId player) {
        // TODO Auto-generated method stub
    }

    public Iterable<Room> getRooms() {
        return roomStore.values();
    }

    public Room getRoom(RoomId roomId) throws ServiceException{
        if (roomStore.containsKey(roomId)){
            return roomStore.get(roomId);
        } else {
            log.error("Room {} do not exist", roomId);
            throw new ServiceException(ResponseCode.badRequest, "Room do not exist");
        }
    }

    @Override
    public RoomId getRoomByTable(TableId tableId) {
        for(Room room : roomStore.values()){
            if (room.tableIds.contains(tableId)){
                return  room.roomId;
            }
        }
        log.error("Can not get Room by Table {}", tableId);
        throw new ServiceException(ResponseCode.badRequest, "Room do not exist");
    }

    public void addRoom(Room room) {
        roomStore.put(room.roomId, room);
    }

    public void removeRoom(RoomId roomId) throws ServiceException{
        if (roomStore.containsKey(roomId)){
            roomStore.remove(roomId);
        } else {
            log.error("Room {} do not exist", roomId);
            throw new ServiceException(ResponseCode.badRequest, "Room do not exist");
        }
    }

    public void addTableToRoom(RoomId roomId, TableId tableId){
        roomStore.get(roomId).addTableId(tableId);
    }

    public void removeTableFromRoom(RoomId roomId, TableId tableId){
        roomStore.get(roomId).removeTableId(tableId);
    }

    @Override
    public void addEventListener(EventListener listener) {
        if (!eventListener.contains(listener)) {
            this.eventListener.add(listener);
        }
    }

    @Override
    public void removeEventListener(EventListener listener) {
        this.eventListener.remove(listener);
    }
    public static RoomService getInstance(){
        return instance;
    }
}
