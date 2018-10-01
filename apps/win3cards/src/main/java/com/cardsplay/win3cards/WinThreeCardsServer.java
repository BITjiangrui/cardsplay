package com.cardsplay.win3cards;

import com.cardsplay.access.api.CardsPlayController;
import com.cardsplay.access.api.CardsPlayNodeId;
import com.cardsplay.access.api.CardsPlayNodeListener;


import java.util.Map;

import static com.cardsplay.win3cards.Main.serviceMap;

public class WinThreeCardsServer {

    public WinThreeCardsServer(){

    }

    public void init(){
        CardsPlayController controller = (CardsPlayController) serviceMap.get(CardsPlayController.class);
        controller.addNodeListener(new playerInternalHadler());

    }
    public class playerInternalHadler implements CardsPlayNodeListener{

        @Override
        public void nodeAdded(CardsPlayNodeId nodeId) {

        }

        @Override
        public void nodeRemoved(CardsPlayNodeId nodeId) {

        }
    }
}
