package com.cardsplay.core.models;

public class TokenWallet {
    public final PlayerId account;

    public final TokenType  tokenType;

    private double amount;

    public TokenWallet(PlayerId account, TokenType tokenType) {
        this.account = account;
        this.tokenType = tokenType;
        this.amount = 0;
    }

    public void add(TokenType tokenType, double money) {
        if (this.tokenType.equals(tokenType)) {
            amount = amount + money;
        } else {
        }
    }

    public void reduce(TokenType tokenType, double money) {
        if (this.tokenType.equals(tokenType)) {
            amount = amount - money;
        } else {
        }
    }

    public double getAmount() {
        return amount;
    }
}
