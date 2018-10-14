package com.cardsplay.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RoomInfoController {

    @RequestMapping(value = "/roomInfos", method = RequestMethod.GET)
    public String RoomInfos() {
        String response = "Hello world";
        return response;
    }
}
