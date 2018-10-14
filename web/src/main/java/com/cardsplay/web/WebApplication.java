package com.cardsplay.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cardsplay.access.api.CardsPlayServerService;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.api.TableService;
import com.cardsplay.core.impl.PlayerManager;
import com.cardsplay.core.impl.RoomManager;
import com.cardsplay.core.impl.ServiceRegistry;
import com.cardsplay.core.impl.TableManager;
import com.cardsplay.win3cards.WinThreeCardsLoadBalancer;


@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
	    // TODO: add graceful shutdown mechanism
	        // init Core Service
	        ServiceRegistry serviceMap = ServiceRegistry.getInstance();
	        RoomService roomService = RoomManager.getInstance();
	        TableService tableService = TableManager.getInstance();
	        PlayerService playerService = PlayerManager.getInstance();
	        serviceMap.registryService(PlayerService.class, playerService);
                serviceMap.registryService(TableService.class, tableService);
	        serviceMap.registryService(RoomService.class, roomService);
	        
	        roomService.activate();
	        tableService.activate();
	        playerService.activate();

	        // init server side
	        CardsPlayServerService server = WinThreeCardsLoadBalancer.getInstance();
	        serviceMap.registryService(CardsPlayServerService.class, server);
	        server.activate();

		SpringApplication.run(WebApplication.class, args);
	}
}
