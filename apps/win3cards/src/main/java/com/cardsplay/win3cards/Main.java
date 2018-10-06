package com.cardsplay.win3cards;

import com.cardsplay.access.api.CardsPlayController;
import com.cardsplay.access.api.CardsPlayServerService;
import com.cardsplay.access.impl.CardsPlayControllerImpl;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.api.TableService;
import com.cardsplay.core.impl.PlayerManager;
import com.cardsplay.core.impl.RoomManager;
import com.cardsplay.core.impl.ServiceRegistry;
import com.cardsplay.core.impl.TableManager;

public class Main {

    public static void main(String[] args) {
       // TODO: add graceful shutdown mechanism
        // init Core Service
        ServiceRegistry serviceMap = ServiceRegistry.getInstance();
        RoomService roomService = RoomManager.getInstance();
        TableService tableService = TableManager.getInstance();
        PlayerService playerService = PlayerManager.getInstance();
        roomService.activate();
        tableService.activate();
        playerService.activate();
        serviceMap.registryService(RoomService.class, roomService);
        serviceMap.registryService(TableService.class, tableService);
        serviceMap.registryService(PlayerService.class, playerService);
        //init access module
        CardsPlayController controller = CardsPlayControllerImpl.getInstance();
        controller.activate();
        serviceMap.registryService(CardsPlayController.class, controller);

        // init server side
        CardsPlayServerService server = WinThreeCardsServer.getInstance();
        server.activate();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hook running...");
                serviceMap.getServices().forEach(v -> {
                    v.deactivate();
                });
                System.out.println("graceful  end...");
            }
        }));
    }
}
