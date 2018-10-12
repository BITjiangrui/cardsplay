package com.cardsplay.core.models;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 荷官，负责整个牌桌玩家的全生命周期的活动
 *
 * </pre>
 */
public abstract class Dealer {
    public final DealType dealType;

    public TokenWallet wallet;
    public Map<PlayerId, List<Card> > playerCards;
    public TableId tableId;
    public int round;
    public double bet;


    public Dealer(DealType dealType) {
        this.dealType = dealType;
        playerCards = Maps.newConcurrentMap();
    }

    public void setTableId(TableId tableId){
        this.tableId = tableId;
    }

    public abstract void init();
}
