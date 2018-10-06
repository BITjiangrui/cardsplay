package com.cardsplay.core.impl;

import com.cardsplay.core.api.EventListener;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.exception.ServiceException;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.PlayerState;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.TableId;
import com.cardsplay.util.ResponseCode;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class PlayerManager implements PlayerService{

    public  final static Logger log = LoggerFactory
            .getLogger(PlayerManager.class);
    Map<PlayerId, Player> playerStore;

    protected Set<EventListener> eventListener = new CopyOnWriteArraySet<>();

    private static PlayerService instance = new PlayerManager();


    private PlayerManager(){
        playerStore = Maps.newConcurrentMap();
    }

    @Override
    public void activate() {
        log.info("Player Service Activated");

    }

    @Override
    public void deactivate() {
        log.info("Player Service Deactivated");

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
    public void playerOnline(Player player) {
        player.setState(PlayerState.Online);
        playerStore.put(player.playerId, player);
    }

    @Override
    public void playerOffline(Player player) {
        player.setState(PlayerState.Offline);
    }

    @Override
    public void playerIsReady(PlayerId playerId) throws ServiceException{
        Player player = playerStore.get(playerId);
        if(player == null){
            log.error("Player {} do not exist", playerId, playerId);
            throw new ServiceException(ResponseCode.badRequest, "玩家不存在，请退出重新加入");
        }
        player.setState(PlayerState.Ready);
    }

    @Override
    public void playerUndoReady(PlayerId playerId) throws ServiceException{
        Player player = playerStore.get(playerId);
        if(player == null){
            log.error("Player {} do not exist", playerId, playerId);
            throw new ServiceException(ResponseCode.badRequest, "玩家不存在，请退出重新加入");
        }
        player.setState(PlayerState.UndoReady);
    }

    @Override
    public void playerBet(PlayerId player, int money) {

    }

    @Override
    public Iterable<Player> getPlayersInRoom(RoomId room) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Player> getPlayersInTable(TableId table) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Player getPlayer(PlayerId playerId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Player> getPlayers() {
        return null;
    }

    public static PlayerService getInstance(){
        return instance;
    }
}
