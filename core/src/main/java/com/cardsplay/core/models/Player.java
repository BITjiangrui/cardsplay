package com.cardsplay.core.models;

 
import java.util.List;
 
/**
 * <pre>
 * �˿������
 * 
 * </pre>
 */
public abstract class Player
{
    public final PlayerId playerId;
    
    /**
     * ��ȡ�����˿�
     */
    public List<Card> porks;
    
    /**
     * Ǯ��
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
     * ��ȡ allPorks
     * 
     * @return ���� allPorks
     */
    public List<Card> getAllPorks()
    {
        return porks;
    }
}
