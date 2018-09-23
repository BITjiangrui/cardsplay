package org.playcards.core.models;

 
import java.util.List;
 
/**
 * <pre>
 * 扑克牌玩家
 * 
 * </pre>
 */
public abstract class Player
{
    public PlayerId playerId;
    
    /**
     * 获取所有扑克
     */
    public List<Card> porks;
    
    /**
     * 钱包
     */
    public TokenWallet wallet;
    
    
    public Player(PlayerId playerId)
    {
        this.playerId = playerId;
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
