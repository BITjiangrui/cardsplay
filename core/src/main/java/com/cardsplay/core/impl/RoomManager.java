package com.cardsplay.core.impl;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.cardsplay.core.api.EventListener;
import com.cardsplay.core.api.EventRegistryService;
import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;

public class RoomManager implements RoomService, EventRegistryService {
    Map<RoomId, Room> RoomStore;
    protected Set<EventListener> eventListener = new CopyOnWriteArraySet<>();

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

}
