package com.cardsplay.core.api;

import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.Table;

public interface PlayerService extends lifeCycleService{
    
    void playerOnline(Player player);
    
    void playerOffline(Player player);

    void getPlayerInRoom(Room room);

    void getPlayerInTable(Table table);
}
