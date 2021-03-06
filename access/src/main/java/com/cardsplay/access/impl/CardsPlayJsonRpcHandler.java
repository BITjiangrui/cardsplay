
package com.cardsplay.access.impl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cardsplay.access.api.CardsPlayNodeId;
import com.cardsplay.access.driver.CardsPlayProviderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Strings;

/**
 * Channel handler deals with the node connection and dispatches
 * cardsplay messages to the appropriate locations.
 */
public final class CardsPlayJsonRpcHandler extends ChannelInboundHandlerAdapter {
    protected final static Logger log = LoggerFactory
            .getLogger(CardsPlayJsonRpcHandler.class);
    private CardsPlayNodeId cardsPlayNodeId;
    private CardsPlayProviderService cardsPlayProviderService;

    /**
     * Constructor from a CardsPlayNodeId cardsPlayNodeId.
     *
     * @param cardsPlayNodeId the cardsPlayNodeId to use
     */
    public CardsPlayJsonRpcHandler(CardsPlayNodeId cardsPlayNodeId) {
        super();
        this.cardsPlayNodeId = cardsPlayNodeId;
    }

    /**
     * Gets the ovsdbProviderService instance.
     *
     * @return the instance of the ovsdbProviderService
     */
    public CardsPlayProviderService getOvsdbProviderService() {
        return cardsPlayProviderService;
    }

    /**
     * Sets the CardsPlayProviderService instance.
     *
     * @param cardsPlayNodeDriver the ovsdbNodeDriver to use
     */
    public void setCardsPlayProviderService(CardsPlayProviderService cardsPlayNodeDriver) {
        this.cardsPlayProviderService = cardsPlayNodeDriver;
    }

    /**
     * Gets the OvsdbNodeId instance.
     *
     * @return the instance of the OvsdbNodeId
     */
    public CardsPlayNodeId getNodeId() {
        return cardsPlayNodeId;
    }

    /**
     * Sets the cardsPlaynode id.
     *
     * @param cardsPlayNode the cardsPlaynode to use
     */
    public void setNodeId(CardsPlayNodeId cardsPlayNode) {
        this.cardsPlayNodeId = cardsPlayNode;
    }

    /**
     * Processes an JsonNode message received on the channel.
     *
     * @param jsonNode The CardsPlayRpcHandler that received the message
     */
    private void processCardsPlayMessage(JsonNode jsonNode) {

        log.debug("Handle CardsPlay message");

        if (jsonNode.has("result")) {

            log.debug("Handle CardsPlay result");
            cardsPlayProviderService.processResult(jsonNode);

        } else if (jsonNode.hasNonNull("method")) {

            log.debug("Handle ovsdb request");
            if (jsonNode.has("id")
                    && !Strings.isNullOrEmpty(jsonNode.get("id").asText())) {
                cardsPlayProviderService.processRequest(jsonNode);
            }

        }
        return;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        log.debug("Receive message from ovsdb");
        if (msg instanceof JsonNode) {
            JsonNode jsonNode = (JsonNode) msg;
            processCardsPlayMessage(jsonNode);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        log.error("Exception inside channel handling pipeline.", cause);
        context.close();
    }
}
