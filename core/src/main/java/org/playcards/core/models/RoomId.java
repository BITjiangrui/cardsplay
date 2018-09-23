package org.playcards.core.models;

import java.util.UUID;

public class RoomId {
	public final UUID roomId;
	
	public final String nickName;
	
	public RoomId(UUID roomId, String nickName) {
		this.nickName = nickName;
		this.roomId = roomId;
	}
	
	public UUID getRoomId() {
		return roomId;
	}
	
	public String getNickName() {
		return nickName;
	}
}
