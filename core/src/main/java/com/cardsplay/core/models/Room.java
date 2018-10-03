package com.cardsplay.core.models;

import com.google.common.collect.Sets;

import java.util.Set;

public class Room {
    public final RoomId roomId;
    public final int capacity;
    public final DealType dealtype;
    public Set<TableId> tableIds;
    public Set<PlayerId> playerIds;

    public  String nickName;

    public Room(RoomId roomId, int capacity, DealType dealtype) {
        this.roomId = roomId;
        tableIds = Sets.newConcurrentHashSet();
        this.capacity = capacity;
        this.dealtype = dealtype;
    }

    public void addTableId(TableId tableId){
        tableIds.add(tableId);
    }

    public void playerJoinIn(PlayerId playerId) {
        playerIds.add(playerId);
    }

    public void playerExit(PlayerId playerId) {
        playerIds.remove(playerId);
    }


    public void removeTableId(TableId tableId){
        tableIds.remove(tableId);
    }

    public String getNickName() {
        return nickName;
    }
}
