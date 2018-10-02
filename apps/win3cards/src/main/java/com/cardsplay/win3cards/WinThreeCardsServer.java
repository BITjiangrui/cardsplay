package com.cardsplay.win3cards;

import com.cardsplay.access.api.CardsPlayController;
import com.cardsplay.access.api.CardsPlayNodeId;
import com.cardsplay.access.api.CardsPlayNodeListener;
import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.models.DealType;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;


import java.util.Map;
import java.util.UUID;

import static com.cardsplay.core.impl.RoomManager.roomCapacity;
import static com.cardsplay.win3cards.Main.serviceMap;

public class WinThreeCardsServer {

    public WinThreeCardsServer(){

    }

    public void init(){
        // init Room
        RoomId roomId = new RoomId(UUID.randomUUID(), 1, "≤‚ ‘");
        Room room = new Room(roomId, roomCapacity, DealType.WinThreeCards);
        RoomService roomService = (RoomService) serviceMap.get(RoomService.class);
        roomService.addRoom(room);


        // init Table

        // add listener
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
