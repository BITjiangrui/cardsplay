package com.cardsplay.core.impl;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import com.cardsplay.core.api.EventListener;
import com.cardsplay.core.api.EventRegistryService;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.models.DealType;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;

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
    
    public boolean joinRoom(RoomId room, PlayerId player) {
        // TODO Auto-generated method stub
        return false;
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
