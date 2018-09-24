package com.cardsplay.access.driver;

import io.netty.channel.Channel;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents the driver side of an CardsPlay node. This interface should never be
 * exposed to consumers.
 */
public interface CardsPlayProviderService {
    /**
     * Sets the CardsPlay agent to be used. This method can only be called once.
     *
     * @param agent the agent to set.
     */
    void setAgent(CardsPlayAgent agent);

    /**
     * Sets the associated Netty channel for this node.
     *
     * @param channel the Netty channel
     */
    void setChannel(Channel channel);

    /**
     * Announces to the CardsPlay agent that this node has added.
     */
    void nodeAdded();

    /**
     * Announces to the CardsPlay agent that this node has removed.
     */
    void nodeRemoved();

    /**
     * Sets whether the node is connected.
     *
     * @param connected whether the node is connected
     */
    void setConnection(boolean connected);

    /**
     * Processes result from CardsPlay client.
     *
     * @param response JsonNode response from CardsPlay
     */
    void processResult(JsonNode response);

    /**
     * processes request from CardsPlay.
     *
     * @param request JsonNode request from CardsPlay.
     */
    void processRequest(JsonNode request);

}
