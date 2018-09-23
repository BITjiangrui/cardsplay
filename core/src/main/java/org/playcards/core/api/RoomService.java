package org.playcards.core.api;

import org.playcards.core.models.Player;
import org.playcards.core.models.Room;
import org.playcards.core.models.RoomId;

public interface RoomService {

	boolean joinRoom(RoomId room, Player player);
	
	boolean quitRoom(RoomId room, Player player);

	Iterable<Room>  getRooms();
	
	Room getRoom(RoomId roomId);
	
	void addRoom(Room room);
	
	void removeRoom(RoomId roomId);
	
	

}
