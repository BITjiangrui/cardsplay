package com.cardsplay.core.models;

import com.google.common.collect.Lists;

import java.util.List;


public class Table {
	public final TableId tableId;
	public  Dealer dealer;
	public List<PlayerId> playerIds;
	public final int capacity;
	public final int seq;


	public Table(TableId tableId,int seq, int volumn) {
		this.tableId = tableId;
		this.seq = seq;
		playerIds = Lists.newArrayListWithCapacity(5);
		capacity =volumn;
	}

	public Table(TableId tableId, int seq, int volumn, List<PlayerId> playerIds) {
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

	public void addPlayer(PlayerId playerId){
		//TODO: to make it thread safe
	}
	
	public boolean isFull() {
	        return playerIds.size() == capacity;
	}
}
