
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cardsplay.access.api.CardsPlayClientService;
import com.cardsplay.access.api.CardsPlayNodeId;
import com.cardsplay.access.util.FromJsonUtil;
import com.cardsplay.core.models.Card;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.PlayerState;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;
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
    public ListenableFuture<List<String>> echo() {
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
        log.debug("Handle result");
        String requestId = response.get("id").asText();
        SettableFuture sf = requestResult.get(requestId);
        if (sf == null) {
            log.debug("No such future to process");
            return;
        }
        String methodName = requestMethod.get(requestId);
        sf.set(FromJsonUtil.jsonResultParser(response, methodName));
        
    }

    @Override
    public void processRequest(JsonNode requestJson) {
        log.debug("Handle request");
        if (requestJson.get("method").asText().equalsIgnoreCase("echo")) {
            log.debug("handle echo request");

            String replyString = FromJsonUtil.getEchoRequestStr(requestJson);
            channel.writeAndFlush(replyString);
        } else {
            
        }        
    }

    @Override
    public void statusChange(RoomId room, TableId table, PlayerId player,
                             PlayerState preStatus, PlayerState currentStatus) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playerJoinIn(RoomId room, TableId table, PlayerId player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playerLeave(RoomId room, TableId table, PlayerId player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ListenableFuture<Boolean> startGamble(RoomId room, TableId table, List<PlayerId> player) {
        // TODO Auto-generated method stub

        return null;        
    }

    @Override
    public ListenableFuture<JsonNode> askForBet(RoomId room, TableId table,
                                                PlayerId player, int money,
                                                int round) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void confirmForBet(RoomId room, TableId table, PlayerId player,
                              int money, int round) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ListenableFuture<Boolean> assignCards(RoomId room, TableId table,
                                                  PlayerId player,
                                                  List<Card> cards) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListenableFuture<Boolean> showCards(RoomId room, TableId table,
                                                PlayerId player,
                                                List<Card> cards) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListenableFuture<List<String>> assignTables(RoomId room,
                                                       TableId table) {
        // TODO Auto-generated method stub
        return null;
    }
}
