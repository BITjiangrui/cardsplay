package com.cardsplay.core.models;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * �ɹ٣���������������ҵ�ȫ�������ڵĻ
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

    //��ʼ���ƾ�
    public abstract void startGamble();

    //ϴ��
    public abstract void shuffle(List<Card> cards);

    //ѯ�ʷ���λ��
    public abstract void askStartLocation();

    // Ҫ����ע
    public abstract void askForBet(PlayerId player, int round);

    // ����Ӯ��
    public abstract Player calcWinner(List<Player> players);

    // ����
    public abstract void balance();
}
