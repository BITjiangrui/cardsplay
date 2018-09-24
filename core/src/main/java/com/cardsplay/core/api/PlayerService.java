package com.cardsplay.core.api;

import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.TableId;


public interface PlayerService extends lifeCycleService{
    
    void playerOnline(PlayerId player);
    
    void playerOffline(PlayerId player);

    void playerIsReady(PlayerId player);

    void playerUndoReady(PlayerId player);

    void getPlayerInRoom(RoomId room);

    void getPlayerInTable(TableId table);
}
