package com.cardsplay.core.impl;

import java.util.Map;

import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.Table;

public class PlayerManager implements PlayerService {
    Map<PlayerId, Player> PlayerStore;

    @Override
    public void playerOnline(Player player) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playerOffline(Player player) {
        // TODO Auto-generated method stub

    }

    @Override
    public void getPlayerInRoom(Room room) {
        // TODO Auto-generated method stub

    }

    @Override
    public void getPlayerInTable(Table table) {
        // TODO Auto-generated method stub

    }

    @Override
    public void activate() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deactivate() {
        // TODO Auto-generated method stub

    }

}
