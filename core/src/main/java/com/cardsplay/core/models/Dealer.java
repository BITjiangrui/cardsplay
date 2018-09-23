package com.cardsplay.core.models;

import java.util.List;

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

	public List<Card> cards;
	
	public TableId tableId;


	public Dealer(DealType dealType) {
		this.dealType = dealType;
	}
	//��ʼ���ƾ�
	public abstract void  init();
	//ϴ��
	public abstract List<Card> shuffle();
	//ѯ�ʷ���λ��
	public abstract int askStartLocation();
	// ����
	public abstract boolean deal(PlayerId player);
	// Ҫ����ע
	public abstract void askForBet(PlayerId player, int amount);
	// ����Ӯ��
	public abstract Player calcWinner(List<Player> players);
	// ����
	public abstract void balance();
}
