package org.playcards.core.models;

import java.util.Map;

public class TokenWallet {
	public final TokenType tokenType;
	
	private Map<PlayerId, Integer> wallet;
	
	public TokenWallet(TokenType tokenType) {
		this.tokenType = tokenType;
	}
	
	public void bet(PlayerId playerId, int money) {
		if(wallet.containsKey(playerId) && wallet.get(playerId)!=null) {
			Integer result = wallet.get(playerId) + money;
			wallet.put(playerId, result);
		} else {
			wallet.put(playerId, money);
		}
	}
}
