package com.cardsplay.core.api;

import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.TableId;

public interface RoomService extends LifeCycleService, EventRegistryService{

	void joinRoom(RoomId room, PlayerId player);
	
	void quitRoom(RoomId room, PlayerId player);

	Iterable<Room>  getRooms();
	
	Room getRoom(RoomId roomId);

	RoomId getRoomByTable(TableId roomId);


	void addRoom(Room room);
	
	void removeRoom(RoomId roomId);

	void addTableToRoom(RoomId roomId, TableId tableId);

	void removeTableFromRoom(RoomId roomId, TableId tableId);

}
