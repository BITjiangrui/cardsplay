package com.cardsplay.core.models;

import java.util.Map;

public class TokenWallet {
    public final PlayerId account;

    private Map<TokenType, Integer> wallet;

    public TokenWallet(PlayerId account) {
        this.account = account;
    }

    public void add(TokenType tokenType, int money) {
        if (wallet.containsKey(tokenType) && wallet.get(tokenType) != null) {
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
