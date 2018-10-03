package com.cardsplay.core.models;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * <pre>
 * ÅÆ×À
 * 
 * </pre>
 */
public class Table {
	public final TableId tableId;
	public final Dealer dealer;
	public Set<PlayerId> playerIds;
	public final int capacity;
	
	public Table(TableId tableId, Dealer dealer, int volumn) {
		this.tableId = tableId;
		this.dealer = dealer;
		playerIds = Sets.newConcurrentHashSet();
		capacity =volumn;
	}

	public Table(TableId tableId, Dealer dealer, int volumn, Set<PlayerId> playerIds) {
		this.tableId = tableId;
		this.dealer = dealer;
		this.playerIds = playerIds;
		capacity =volumn;
	}

	protected void removePlayer(PlayerId playerId){
		playerIds.remove(playerId);
	}
}
