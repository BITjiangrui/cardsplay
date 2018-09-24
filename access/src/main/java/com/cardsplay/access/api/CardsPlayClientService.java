/*
 * Copyright 2015-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cardsplay.access.api;

import java.util.List;

import com.cardsplay.core.models.Card;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerState;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.Table;
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
    void statusChange(Room room, Table table, Player player, PlayerState preStatus, PlayerState currentStatus);
  
    /**
     * This operation notify client the Player join in event
     *
     */
    void playerJoinIn(Room room, Table table, Player player);
    
    
    /**
     * This operation notify client the Player leave event
     *
     */
    void playerLeave(Room room, Table table, Player player);

    /**
     * This operation notify client the Game start event
     *
     */
    void startGamble(Room room, Table table, List<Player> player);
    /**
     * This operation ask player to bet
     *
     */
    ListenableFuture<JsonNode> askForBet(Room room, Table table, Player player, int money, int round);


    /**
     * This operation confirm the bet to other player to bet
     *
     */
    void confirmForBet(Room room, Table table, Player player, int money, int round);

    /**
     * The "echo" method can be used by both clients and servers to verify the
     * liveness of a game connection.
     */
    ListenableFuture<List<String>> echo();

    /**
     * The "assign Cards" request to send three cards to player client
     */
    ListenableFuture<JsonNode> assignCards(Room room, Table table, Player player, List<Card> cards);

    
    /**
     * The "show Cards" request to show all three cards to other player client
     */
    ListenableFuture<JsonNode> showCards(Room room, Table table, Player player, List<Card> cards);
    
    /**
     * This operation retrieves a scheduled table by Platform
     */
    ListenableFuture<List<String>> assignTables(Room room, Table table);

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
