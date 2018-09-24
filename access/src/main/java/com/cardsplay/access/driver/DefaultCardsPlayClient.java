
package com.cardsplay.access.driver;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.Collections;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cardsplay.access.api.CardsPlayClientService;
import com.cardsplay.access.api.CardsPlayNodeId;
import com.cardsplay.core.models.Card;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerState;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.Table;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;


/**
 * An representation of an ovsdb client.
 */
public class DefaultCardsPlayClient implements CardsPlayProviderService, CardsPlayClientService {

    private static final int TRANSACTCONFIG_TIMEOUT = 3; //sec

    private final Logger log = LoggerFactory.getLogger(DefaultCardsPlayClient.class);

    private Channel channel;
    private CardsPlayAgent agent;
    private boolean connected;
    private CardsPlayNodeId nodeId;

    private final Map<String, String> requestMethod = Maps.newHashMap();
    private final Map<String, SettableFuture<? extends Object>> requestResult = Maps.newHashMap();


    /**
     * Creates an DefaultW3cClient.
     *
     * @param nodeId CardsPlay node id
     */
    public DefaultCardsPlayClient(CardsPlayNodeId nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public CardsPlayNodeId nodeId() {
        return nodeId;
    }

    @Override
    public void setAgent(CardsPlayAgent agent) {
        if (this.agent == null) {
            this.agent = agent;
        }
    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void setConnection(boolean connected) {
        this.connected = connected;
    }

    @Override
    public boolean isConnected() {
        return this.connected;
    }

    @Override
    public void nodeAdded() {
        this.agent.addConnectedNode(nodeId, this);
    }

    @Override
    public void nodeRemoved() {
        this.agent.removeConnectedNode(nodeId);
        channel.disconnect();
    }

    @Override
    public void statusChange(Room room, Table table, Player player,
                             PlayerState preStatus, PlayerState currentStatus) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playerJoinIn(Room room, Table table, Player player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playerLeave(Room room, Table table, Player player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void startGamble(Room room, Table table, List<Player> player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ListenableFuture<JsonNode> askForBet(Room room, Table table,
                                                Player player, int money,
                                                int round) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void confirmForBet(Room room, Table table, Player player, int money,
                              int round) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ListenableFuture<List<String>> echo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListenableFuture<JsonNode> assignCards(Room room, Table table,
                                                  Player player,
                                                  List<Card> cards) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListenableFuture<JsonNode> showCards(Room room, Table table,
                                                Player player,
                                                List<Card> cards) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListenableFuture<List<String>> assignTables(Room room, Table table) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void showRoom(Room room) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void disconnect() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void processResult(JsonNode response) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void processRequest(JsonNode request) {
        // TODO Auto-generated method stub
        
    }
}
