package com.cardsplay.core.models;

import static com.google.common.base.MoreObjects.toStringHelper;

public enum Rule {

    NEWBEE(new Bet(0.1), 2,10),  EXPERT(new Bet(0.5), 10, 50), MASTER(new Bet(1),20, 100);

    private  final Bet singleBet;

    // upper limit in single round
    private  final double upperLimit;

    private final double balance;

    private Rule(Bet singleBet, int upperLimit, int balance){
        this.singleBet = singleBet;
        this.upperLimit = upperLimit;
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }


    public Bet getSingleBet() {
        return singleBet;
    }


    public double getUpperLimit(){
        return upperLimit;
    }
    
    @Override
    public String toString() {
        return toStringHelper(this).add("singleBet", singleBet)
                .add("upperLimit", upperLimit)
                .add("balance", balance)
                .toString();

    }
}
