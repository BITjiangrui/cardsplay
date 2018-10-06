package com.cardsplay.core.models;

public enum Rule {

    NEWBEE(new Bet(0.1), 2,10),  EXPERT(new Bet(0.5), 10, 50), MASTER(new Bet(1),20, 100);

    // 单注
    private  Bet singleBet;

    // 单回合最高下注
    private  int upperLimit;

    // 钱包余额
    private int balance;

    private Rule(Bet singleBet, int upperLimit, int balance){
        this.singleBet = singleBet;
        this.upperLimit = upperLimit;
        this.balance = balance;
    }
}
