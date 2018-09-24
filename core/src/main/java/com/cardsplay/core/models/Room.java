package com.cardsplay.core.models;

import java.util.Set;

import com.google.common.collect.Sets;

public class Room {
        public final RoomId roomId;
	public Set<TableId> tableIds;
        public Set<PlayerId> playerIds;
	public final int capacity;
	public final DealType dealtype;

	public Room(RoomId roomId, int capacity, DealType dealtype) {
	    this.roomId = roomId;
	    tableIds = Sets.newConcurrentHashSet();
	    this.capacity = capacity;
	    this.dealtype = dealtype;
	}
}
