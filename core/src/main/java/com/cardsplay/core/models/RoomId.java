package com.cardsplay.core.models;

import java.util.UUID;

public class RoomId {
	public final UUID roomId;

	public final int seq;

	public final String nickName;
	
	public RoomId(UUID roomId, int seq, String nickName) {
		this.nickName = nickName;
		this.roomId = roomId;
		this.seq = seq;
	}
	
	public UUID getRoomId() {
		return roomId;
	}
	
	public String getNickName() {
		return nickName;
	}

	public int getSeq(){return seq;}
}
