package com.cardsplay.core.models;

import java.util.Map;

public class TokenWallet {
    public final PlayerId account;

    public final TokenType  tokenType;
    
    private Integer amount;

    public TokenWallet(PlayerId account, TokenType tokenType) {
        this.account = account;
        this.tokenType = tokenType;
        this.amount = 0;
    }

    public void add(TokenType tokenType, int money) {
        if (this.tokenType.equals(tokenType)) {
            Integer result = wallet.get(tokenType) + money;
            wallet.put(tokenType, result);
        } else {
            wallet.put(tokenType, money);
        }
    }

    public void reduce(TokenType tokenType, int money) {
        if (wallet.containsKey(tokenType) && wallet.get(tokenType) != null) {
            Integer result = wallet.get(tokenType) + money;
            wallet.put(tokenType, result);
        } else {
            wallet.put(tokenType, money);
        }
    }
}
