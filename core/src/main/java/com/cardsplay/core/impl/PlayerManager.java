package com.cardsplay.core.impl;

import com.cardsplay.core.api.Event;
import com.cardsplay.core.api.EventListener;
import com.cardsplay.core.api.PlayerEvent;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.api.TableService;
import com.cardsplay.core.exception.ServiceException;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.PlayerState;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;
import com.cardsplay.util.ResponseCode;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class PlayerManager implements PlayerService{

    public  final static Logger log = LoggerFactory
            .getLogger(PlayerManager.class);
    Map<PlayerId, Player> playerStore;

    protected Set<EventListener> eventListener = new CopyOnWriteArraySet<>();

    private static PlayerService instance = new PlayerManager();
    ServiceRegistry serviceMap = ServiceRegistry.getInstance();
    RoomService roomService = (RoomService) serviceMap.getService(RoomService.class);
    TableService tableService = (TableService) serviceMap.getService(TableService.class);

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
        PlayerEvent playerEvent = new PlayerEvent(PlayerEvent.Type.PLAYER_ONLINE, player);
        post(playerEvent);

    }

    @Override
    public void playerOffline(Player player) {
        player.setState(PlayerState.Offline);
        PlayerEvent playerEvent = new PlayerEvent(PlayerEvent.Type.PLAYER_OFFLINE, player);
        post(playerEvent);
    }

    @Override
    public void playerIsReady(PlayerId playerId) throws ServiceException{
        Player player = playerStore.get(playerId);
        if(player == null){
            log.error("Player {} do not exist", playerId, playerId);
            throw new ServiceException(ResponseCode.badRequest, "玩家不存在，请退出重新加入");
        }
        Player prePlayer = new Player(playerId, player.wallet);
        prePlayer.state = player.state;
        prePlayer.nickName = player.nickName;
        player.setState(PlayerState.Ready);
        PlayerEvent playerEvent = new PlayerEvent(PlayerEvent.Type.PLAYER_READY, prePlayer, player);
        post(playerEvent);
    }

    @Override
    public void playerUndoReady(PlayerId playerId) throws ServiceException{
        Player player = playerStore.get(playerId);
        if(player == null){
            log.error("Player {} do not exist", playerId, playerId);
            throw new ServiceException(ResponseCode.badRequest, "玩家不存在，请退出重新加入");
        }
        Player prePlayer = new Player(playerId, player.wallet);
        prePlayer.state = player.state;
        prePlayer.nickName = player.nickName;
        player.setState(PlayerState.Online);
        PlayerEvent playerEvent = new PlayerEvent(PlayerEvent.Type.PLAYER_UNDOREADY, prePlayer, player);
        post(playerEvent);
    }

    @Override
    public void removePlayer(PlayerId playerId) {
        playerStore.remove(playerId);
    }

    @Override
    public void playerBet(PlayerId player, int money) {

    }

    @Override
    public Iterable<Player> getPlayersInRoom(RoomId roomId) throws ServiceException{
        Room room = roomService.getRoom(roomId);
        Set<Player> players = Sets.newConcurrentHashSet();
        for(PlayerId playerId : room.playerIds){
            players.add(getPlayer(playerId));
        }
        return players;
    }

    @Override
    public Iterable<Player> getPlayersInTable(TableId tableId) throws ServiceException {
        Table table = tableService.getTable(tableId);
        Set<Player> players = Sets.newConcurrentHashSet();
        for (PlayerId playerId : table.playerIds){
            players.add(getPlayer(playerId));
        }
        return players;
    }

    @Override
    public Iterable<PlayerId> getOtherPlayers(PlayerId playerId) throws ServiceException{
        Table table = tableService.getTableByPlayer(playerId);
        List otherPlayers = table.playerIds;
        otherPlayers.remove(playerId);
        return otherPlayers;
    }

    @Override
    public Player getPlayer(PlayerId playerId) throws ServiceException{
        if (playerStore.containsKey(playerId)){
            return playerStore.get(playerId);
        } else {
            log.error("Player {} do not exist", playerId);
            throw new ServiceException(ResponseCode.badRequest, "玩家不存在");
        }    }

    @Override
    public PlayerState getPlayerState(PlayerId playerId) {
        return playerStore.get(playerId).getState();
    }

    @Override
    public Iterable<Player> getPlayers() {
        return playerStore.values();
    }

    public static PlayerService getInstance(){
        return instance;
    }

    private void  post(Event playerEvenr){
        for(EventListener listener : eventListener){
            listener.event(playerEvenr);
        }
    }
}
