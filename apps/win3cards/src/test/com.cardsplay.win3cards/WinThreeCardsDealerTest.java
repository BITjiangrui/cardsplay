package com.cardsplay.win3cards;

import com.cardsplay.core.models.Rule;
import org.junit.Test;

public class WinThreeCardsDealerTest {
    @Test
    public void startGamble(){
        WinThreeCardsDealer dealer = new WinThreeCardsDealer(Rule.NEWBEE);
        dealer.startGamble();
    }
}
