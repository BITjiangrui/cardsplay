package com.cardsplay.core.models;

import java.util.UUID;

public class RoomId {
	public final UUID roomId;

	public final int seq;

	public RoomId(UUID roomId, int seq) {
		this.roomId = roomId;
		this.seq = seq;
	}
	
	public UUID getRoomId() {
		return roomId;
	}

	public int getSeq(){return seq;}
}
