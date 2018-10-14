package com.cardsplay.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cardsplay.access.api.CardsPlayServerService;
import com.cardsplay.core.api.ClientResponse;
import com.cardsplay.core.impl.ServiceRegistry;
import com.cardsplay.win3cards.WinThreeCardsLoadBalancer;



@RestController
public class RoomInfoController {
    ServiceRegistry serviceMap = ServiceRegistry.getInstance();
    CardsPlayServerService server = (CardsPlayServerService) serviceMap.getService(CardsPlayServerService.class);
    @RequestMapping(value = "/roomInfos", method = RequestMethod.GET)
    public ClientResponse RoomInfos() {
        ClientResponse response = server.getRoomsInfo();
        return response;
    }
}
