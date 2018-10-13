
package com.cardsplay.access.api;

import com.cardsplay.core.api.ClientResponse;
import com.cardsplay.core.models.Card;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.PlayerState;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.Rule;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

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
    void playerJoinIn(RoomId room, Table preTable, Table currentTable);
    
    
    /**
     * This operation notify client the Player leave event
     *
     */
    void playerLeave(RoomId room, Table preTable, Table currentTable);

    /**
     * This operation notify client the Game start event
     *
     */
    ListenableFuture<ClientResponse> startGamble(RoomId room, TableId table);

    /**
     * This operation ask player to bet
     *
     */
    ListenableFuture<ClientResponse> askForBet(RoomId room, TableId table, PlayerId player, Double singleBet, int round);


    /**
     * This operation confirm the bet to other player to bet
     *
     */
    void confirmForBet(RoomId room, TableId table, PlayerId player, Rule money, int round);

    /**
     * The "echo" method can be used by both clients and servers to verify the
     * liveness of a game connection.
     */
    void echo();

    /**
     * The "assign Cards" request to send three cards to player client
     */
    ListenableFuture<ClientResponse> assignCards(RoomId room, TableId table, PlayerId player, List<Card> cards);

    
    /**
     * The "show Cards" request to show all three cards to other player client
     */
    ListenableFuture<ClientResponse> showCards(RoomId room, TableId table, PlayerId player, List<Card> cards);
    
    /**
     * This operation retrieves a scheduled table by Platform
     */
    ListenableFuture<ClientResponse> assignTables(RoomId room, TableId table);

    /**
     * This RPC method shows the all Info in the specific room
     */
    void showRoom(ClientResponse response);

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
    

    /**
     * Ask for start location
     */
    ListenableFuture<Integer> askForStartLocation(PlayerId playerId);
}
