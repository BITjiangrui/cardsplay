package com.cardsplay.win3cards.models;

import java.util.List;

import com.cardsplay.core.models.Card;
import com.cardsplay.core.models.DealType;
import com.cardsplay.core.models.Dealer;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerId;

public class WinThreeCardsDealer extends Dealer {

    public WinThreeCardsDealer(DealType dealType) {
        super(dealType);
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Card> shuffle() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int askStartLocation() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean deal(PlayerId player) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void askForBet(PlayerId player, int amount) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Player calcWinner(List<Player> players) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void balance() {
        // TODO Auto-generated method stub
        
    }

}
