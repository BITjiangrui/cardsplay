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
	abstract void  init();
	//ϴ��
	abstract List<Card> shuffle();
	//ѯ�ʷ���λ��
	abstract int askStartLocation();
	// ����
	abstract boolean deal(PlayerId player);
	// Ҫ����ע
	abstract void askForBet(PlayerId player, int amount);
	// ����Ӯ��
	abstract Player calcWinner(List<Player> players);
	// ����
	abstract void balance();
}
