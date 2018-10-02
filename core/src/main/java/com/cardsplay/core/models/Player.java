package com.cardsplay.core.models;


import java.util.List;

/**
 * <pre>
 * �˿������
 *
 * </pre>
 */
public class Player {
    public final PlayerId playerId;
    /**
     * Ǯ��
     */
    public final TokenWallet wallet;
    /**
     * ��ȡ�����˿�
     */
    public List<Card> porks;

    public String nickName;


    public PlayerState state;

    public Player(PlayerId playerId, TokenWallet wallet) {
        this.playerId = playerId;
        this.wallet = wallet;
    }

    public PlayerState getState() {
        return state;
    }

    public  void  setState(PlayerState state) {
        this.state = state;
    }

    public  void setNickName(String nickName){
        this.nickName = nickName;
    }

    public  String getNickName(){
        return nickName;
    }
    /**
     * ��ȡ allPorks
     *
     * @return ���� allPorks
     */
    public List<Card> getAllPorks() {
        return porks;
    }

    public void deposit(int money) {

    }

    public void draw(int money) {

    }
}
