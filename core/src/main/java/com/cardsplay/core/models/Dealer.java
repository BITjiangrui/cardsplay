package com.cardsplay.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;


public abstract class Dealer {
    public final DealType dealType;

    @JsonIgnore
    public Map<PlayerId, List<Card> > playerCards;
    @JsonIgnore
    public TableId tableId;
    @JsonIgnore
    public int round;


    public Dealer(DealType dealType) {
        this.dealType = dealType;
        playerCards = Maps.newConcurrentMap();
    }

    public void setTableId(TableId tableId){
        this.tableId = tableId;
    }

    public abstract void init();
}
