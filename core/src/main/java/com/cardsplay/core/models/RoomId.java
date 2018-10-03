package com.cardsplay.core.models;

import java.util.UUID;

public class RoomId {
	public final UUID roomId;

	public RoomId(UUID roomId) {
		this.roomId = roomId;
	}
	
	public UUID getRoomId() {
		return roomId;
	}
}
