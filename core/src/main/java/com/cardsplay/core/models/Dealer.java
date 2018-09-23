package com.cardsplay.core.models;

import java.util.List;

/**
 * <pre>
 * 荷官，负责整个牌桌玩家的全生命周期的活动
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
	//初始化牌局
	public abstract void  init();
	//洗牌
	public abstract List<Card> shuffle();
	//询问发牌位置
	public abstract int askStartLocation();
	// 发牌
	public abstract boolean deal(PlayerId player);
	// 要求下注
	public abstract void askForBet(PlayerId player, int amount);
	// 计算赢家
	public abstract Player calcWinner(List<Player> players);
	// 结算
	public abstract void balance();
}
