package com.cardsplay.core.api;

import java.util.List;

import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.TableId;


public interface PlayerService extends LifeCycleService, EventRegistryService{
    
    void playerOnline(Player player);
    
    void playerOffline(Player player);

    void playerIsReady(PlayerId player);

    void playerUndoReady(PlayerId player);
    
    void playerBet(PlayerId player, int money);

    void playerWin(PlayerId player, int money);

    Iterable<Player> getPlayersInRoom(RoomId room);

    Iterable<Player> getPlayersInTable(TableId table);
    
    Player getPlayer(PlayerId playerId);

    Iterable<Player> getPlayers();


}
