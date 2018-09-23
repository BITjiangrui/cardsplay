package org.playcards.core.impl;

import java.util.Map;

import org.playcards.core.api.RoomService;
import org.playcards.core.models.Player;
import org.playcards.core.models.Room;
import org.playcards.core.models.RoomId;
import org.playcards.core.models.Table;
import org.playcards.core.models.TableId;
import org.playcards.core.models.TableStatus;

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

}
