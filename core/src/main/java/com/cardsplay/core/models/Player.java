package com.cardsplay.core.models;

 
import java.util.List;
 
/**
 * <pre>
 * 扑克牌玩家
 * 
 * </pre>
 */
public abstract class Player
{
    public final PlayerId playerId;
    
    /**
     * 获取所有扑克
     */
    public List<Card> porks;
    
    /**
     * 钱包
     */
    public final TokenWallet wallet;
    
 
    public PlayerState state;
    
    public Player(PlayerId playerId, TokenWallet wallet)
    {
        this.playerId = playerId;
        this.wallet = wallet;
    }
    
    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    /**
     * 获取 allPorks
     * 
     * @return 返回 allPorks
     */
    public List<Card> getAllPorks()
    {
        return porks;
    }
}
