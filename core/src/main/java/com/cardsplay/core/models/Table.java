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
	public  Dealer dealer;
	public Set<PlayerId> playerIds;
	public final int capacity;
	public final int seq;


	public Table(TableId tableId,int seq, int volumn) {
		this.tableId = tableId;
		this.seq = seq;
		playerIds = Sets.newConcurrentHashSet();
		capacity =volumn;
	}

	public Table(TableId tableId, int seq, int volumn, Set<PlayerId> playerIds) {
		this(tableId, seq, volumn);
		this.playerIds = playerIds;
	}

	public  void setDealer(Dealer dealer){
		this.dealer = dealer;
	}

	public Dealer getDealer(){
		return  dealer;
	}

	public void removePlayer(PlayerId playerId){
		playerIds.remove(playerId);
	}
}
