package com.cardsplay.win3cards;

import com.cardsplay.access.api.CardsPlayController;
import com.cardsplay.access.api.CardsPlayServerService;
import com.cardsplay.access.impl.CardsPlayControllerImpl;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.api.TableService;
import com.cardsplay.core.impl.PlayerManager;
import com.cardsplay.core.impl.RoomManager;
import com.cardsplay.core.impl.TableManager;
import com.google.common.collect.Maps;

import java.util.Map;

public class Main {
    public static Map serviceMap = Maps.newConcurrentMap();
    public static void main(String[] args) {
        // init Core Service
        PlayerService playerService = PlayerManager.getInstance();
        RoomService roomService = RoomManager.getInstance();
        TableService tableService = TableManager.getInstance();
        roomService.activate();
        tableService.activate();
        playerService.activate();
        serviceMap.put(PlayerService.class, playerService);
        serviceMap.put(RoomService.class, roomService);
        serviceMap.put(TableService.class, tableService);

        //init access module
        CardsPlayController controller = CardsPlayControllerImpl.getInstance();
        controller.activate();
        serviceMap.put(CardsPlayController.class, controller);

        // init server side
        CardsPlayServerService server = WinThreeCardsServer.getInstance();
        server.activate();
    }
}
