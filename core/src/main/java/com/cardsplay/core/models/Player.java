package com.cardsplay.core.models;


import java.util.List;

/**
 * <pre>
 * 扑克牌玩家
 *
 * </pre>
 */
public class Player {
    public final PlayerId playerId;
    /**
     * 钱包
     */
    public final TokenWallet wallet;
    /**
     * 获取所有扑克
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
     * 获取 allPorks
     *
     * @return 返回 allPorks
     */
    public List<Card> getAllPorks() {
        return porks;
    }

    public void deposit(int money) {

    }

    public void draw(int money) {

    }
}
