package com.cardsplay.core.models;

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
	
	private int round;

	public Map<PlayerId, Card> playerCards;
	
	public TableId tableId;


	public Dealer(DealType dealType) {
		this.dealType = dealType;
	}
	       //��ʼ���ƾ�
        public abstract void  startGamble(int money);
        //ϴ��
        public abstract List<Card> shuffle();
        //ѯ�ʷ���λ��
        public abstract int askStartLocation();
        // ����
        public abstract boolean assignCards(PlayerId player, List<Card> cards);
        // Ҫ����ע
        public abstract void askForBet(PlayerId player, int round);
        // ����Ӯ��
        public abstract Player calcWinner(List<Player> players);
        // ����
        public abstract void balance();
}
