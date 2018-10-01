package com.cardsplay.core.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


import com.cardsplay.core.api.Event;
import com.cardsplay.core.api.EventListener;
import com.cardsplay.core.api.EventRegistryService;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;

public class PlayerManager implements PlayerService{

    Map<PlayerId, Player> PlayerStore;

    protected Set<EventListener> eventListener = new CopyOnWriteArraySet<>();

    private static PlayerService instance = new PlayerManager();


    private PlayerManager(){
    }

    @Override
    public void activate() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deactivate() {
        // TODO Auto-generated method stub

    }


    @Override
    public void addEventListener(EventListener listener) {
        if (!eventListener.contains(listener)) {
            this.eventListener.add(listener);
        }
    }

    @Override
    public void removeEventListener(EventListener listener) {
        this.eventListener.remove(listener);
    }

    @Override
    public void playerOnline(PlayerId player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playerOffline(PlayerId player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playerIsReady(PlayerId player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playerUndoReady(PlayerId player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playerBet(PlayerId player, int money) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playerWin(PlayerId player, int money) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Player> getPlayersInRoom(RoomId room) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Player> getPlayersInTable(TableId table) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Player getPlayer(PlayerId playerId) {
        // TODO Auto-generated method stub
        return null;
    }

    public static PlayerService getInstance(){
        return instance;
    }
}
