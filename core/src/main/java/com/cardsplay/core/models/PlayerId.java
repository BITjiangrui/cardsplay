package com.cardsplay.core.models;

import java.util.UUID;

public class PlayerId {
	
	public final UUID playerId;

	public PlayerId(UUID playerId) {
		this.playerId = playerId;
	}
	
	public UUID getPlayId() {
		return playerId;
	}
}
