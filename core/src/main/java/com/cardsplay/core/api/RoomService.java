package com.cardsplay.core.api;

import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;

public interface RoomService extends LifeCycleService, EventRegistryService{

	boolean joinRoom(RoomId room, PlayerId player);
	
	boolean quitRoom(RoomId room, PlayerId player);

	Iterable<Room>  getRooms();
	
	Room getRoom(RoomId roomId);
	
	void addRoom(Room room);
	
	void removeRoom(RoomId roomId);
	
	

}
