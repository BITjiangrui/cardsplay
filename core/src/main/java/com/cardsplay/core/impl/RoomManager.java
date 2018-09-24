package com.cardsplay.core.impl;

import java.util.Map;

import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;
import com.cardsplay.core.models.TableStatus;

public class RoomManager implements RoomService {
    Map<RoomId, Room> RoomStore;

    public boolean joinRoom(RoomId room, Player player) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean quitRoom(RoomId room, Player player) {
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
    public void activate() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deactivate() {
        // TODO Auto-generated method stub

    }

}
