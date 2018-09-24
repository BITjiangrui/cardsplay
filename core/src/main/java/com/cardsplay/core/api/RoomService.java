package com.cardsplay.core.api;

import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;

public interface RoomService extends lifeCycleService{

	boolean joinRoom(RoomId room, Player player);
	
	boolean quitRoom(RoomId room, Player player);

	Iterable<Room>  getRooms();
	
	Room getRoom(RoomId roomId);
	
	void addRoom(Room room);
	
	void removeRoom(RoomId roomId);
	
	

}
