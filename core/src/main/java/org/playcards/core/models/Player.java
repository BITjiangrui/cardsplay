package org.playcards.core.models;

 
import java.util.List;
 
/**
 * <pre>
 * �˿������
 * 
 * </pre>
 */
public abstract class Player
{
    public PlayerId playerId;
    
    /**
     * ��ȡ�����˿�
     */
    public List<Card> porks;
    
    /**
     * Ǯ��
     */
    public TokenWallet wallet;
    
    
    public Player(PlayerId playerId)
    {
        this.playerId = playerId;
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
