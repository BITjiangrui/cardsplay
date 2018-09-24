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
package com.cardsplay.access.impl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cards.access.driver.CardsPlayProviderService;
import com.cardsplay.access.api.CardsPlayNodeId;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Strings;

/**
 * Channel handler deals with the node connection and dispatches
 * cardsplay messages to the appropriate locations.
 */
public final class CardsPlayJsonRpcHandler extends ChannelInboundHandlerAdapter {
    protected static final Logger log = LoggerFactory
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
     * Sets the ovsdbProviderService instance.
     *
     * @param ovsdbNodeDriver the ovsdbNodeDriver to use
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
     * @param cardsPlaynode the cardsPlaynode to use
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
