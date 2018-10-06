package com.cardsplay.core.models;


/**
 * <pre>
 * ÆË¿ËÅÆÍæ¼Ò
 *
 * </pre>
 */
public class Player {
    public final PlayerId playerId;
    /**
     * Ç®°ü
     */
    public final TokenWallet wallet;

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


    public void deposit(int money) {

    }

    public void draw(int money) {

    }
}
