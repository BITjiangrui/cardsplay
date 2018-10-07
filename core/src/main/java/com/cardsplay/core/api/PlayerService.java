package com.cardsplay.core.api;

import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.TableId;


public interface PlayerService extends LifeCycleService, EventRegistryService{
    
    void playerOnline(Player player);
    
    void playerOffline(Player player);

    void playerIsReady(PlayerId player);

    void playerUndoReady(PlayerId player);

    void removePlayer(PlayerId playerId);

    void playerBet(PlayerId player, int money);

    Iterable<Player> getPlayersInRoom(RoomId room);

    Iterable<Player> getPlayersInTable(TableId table);

    Iterable<PlayerId> getOtherPlayers(PlayerId playerId);

    Player getPlayer(PlayerId playerId);

    Iterable<Player> getPlayers();


}
