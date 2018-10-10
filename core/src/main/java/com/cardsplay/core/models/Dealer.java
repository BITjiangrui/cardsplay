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


    public Dealer(DealType dealType) {
        this.dealType = dealType;
        playerCards = Maps.newConcurrentMap();
    }

    public void setTableId(TableId tableId){
        this.tableId = tableId;
    }

    //初始化牌局
    public abstract void startGamble();

    //洗牌
    public abstract void shuffle(List<Card> cards);

    //询问发牌位置
    public abstract void askStartLocation();

    // 要求下注
    public abstract void askForBet(PlayerId player, int round);

    // 计算赢家
    public abstract Player calcWinner(List<Player> players);

    // 结算
    public abstract void balance();
}
