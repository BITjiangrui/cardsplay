
package com.cardsplay.access.api;

import java.util.List;

import com.cardsplay.core.models.Card;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.PlayerState;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.TableId;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.util.concurrent.ListenableFuture;

/**
 * Represents to provider facing side of a client.
 */
public interface CardsPlayClientService{
    /**
     * Gets the node identifier.
     *
     * @return node identifier
     */
    CardsPlayNodeId nodeId();

    
    /**
     * This operation notify client the player state change event
     *
     */
    void statusChange(RoomId room, TableId table, PlayerId player, PlayerState preStatus, PlayerState currentStatus);
  
    /**
     * This operation notify client the Player join in event
     *
     */
    void playerJoinIn(RoomId room, TableId table, PlayerId player);
    
    
    /**
     * This operation notify client the Player leave event
     *
     */
    void playerLeave(RoomId room, TableId table, PlayerId player);

    /**
     * This operation notify client the Game start event
     *
     */
    ListenableFuture<Boolean> startGamble(RoomId room, TableId table, List<PlayerId> player);

    /**
     * This operation ask player to bet
     *
     */
    ListenableFuture<JsonNode> askForBet(RoomId room, TableId table, PlayerId player, int money, int round);


    /**
     * This operation confirm the bet to other player to bet
     *
     */
    void confirmForBet(RoomId room, TableId table, PlayerId player, int money, int round);

    /**
     * The "echo" method can be used by both clients and servers to verify the
     * liveness of a game connection.
     */
    ListenableFuture<List<String>> echo();

    /**
     * The "assign Cards" request to send three cards to player client
     */
    ListenableFuture<Boolean> assignCards(RoomId room, TableId table, PlayerId player, List<Card> cards);

    
    /**
     * The "show Cards" request to show all three cards to other player client
     */
    ListenableFuture<Boolean> showCards(RoomId room, TableId table, PlayerId player, List<Card> cards);
    
    /**
     * This operation retrieves a scheduled table by Platform
     */
    ListenableFuture<List<String>> assignTables(RoomId room, TableId table);

    /**
     * This RPC method shows the all Info in the specific room¡£
     */
    void showRoom(Room room);

    /**
     * Checks if the node is still connected.
     *
     * @return true if the node is still connected
     */
    boolean isConnected();


    /**
     * Disconnects the OVSDB server.
     */
    void disconnect();
}
