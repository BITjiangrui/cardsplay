package com.cardsplay.core.impl;

import com.cardsplay.core.api.EventListener;
import com.cardsplay.core.api.RoomService;
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

    public static int roomCapacity = 250;

    private RoomManager(){
        roomStore = Maps.newConcurrentMap();
    };
    @Override
    public void activate() {
        log.info("Room Service Activated");
    }

    @Override
    public void deactivate() {
        log.info("Room Service Deactivated");

    }
    
    public Room joinRoom(RoomId roomId, PlayerId player) {
        Room room = roomStore.get(roomId);
        room.playerJoinIn(player);
        return room;
    }

    public void quitRoom(RoomId room, PlayerId player) {
        // TODO Auto-generated method stub
    }

    public Iterable<Room> getRooms() {
        // TODO Auto-generated method stub
        return null;
    }

    public Room getRoom(RoomId roomId) {
        // TODO Auto-generated method stub
        return null;
    }

    public void addRoom(Room room) {
        roomStore.put(room.roomId, room);
    }

    public void removeRoom(RoomId roomId) {
        // TODO Auto-generated method stub

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
