package com.cardsplay.core.models;

import java.util.UUID;

public class PlayerId {
	
	public final UUID playerId;
	
	public final String nickName;
	
	public PlayerId(UUID playerId, String nickName) {
		this.nickName = nickName;
		this.playerId = playerId;
	}
	
	public UUID getPlayId() {
		return playerId;
	}
	
	public String getNickName() {
		return nickName;
	}
}
