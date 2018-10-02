package com.cardsplay.core.impl;

import com.cardsplay.core.api.EventListener;
import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.TableId;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class RoomManager implements RoomService {
    Map<RoomId, Room> RoomStore;


    protected Set<EventListener> eventListener = new CopyOnWriteArraySet<>();

    private static RoomService instance = new RoomManager();

    public static int roomCapacity = 250;

    private RoomManager(){};
    @Override
    public void activate() {
        // TODO Auto-generated method stub
    }

    @Override
    public void deactivate() {
        // TODO Auto-generated method stub

    }
    
    public Room joinRoom(RoomId roomId, PlayerId player) {
        Room room = RoomStore.get(roomId);
        room.playerJoinIn(player);
        return room;
    }

    public boolean quitRoom(RoomId room, PlayerId player) {
        // TODO Auto-generated method stub
        return false;
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
        // TODO Auto-generated method stub

    }

    public void removeRoom(RoomId roomId) {
        // TODO Auto-generated method stub

    }

    public void addTableToRoom(RoomId roomId, TableId tableId){
        RoomStore.get(roomId).addTableId(tableId);
    }

    public void removeTableFromRoom(RoomId roomId, TableId tableId){
        RoomStore.get(roomId).removeTableId(tableId);
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
