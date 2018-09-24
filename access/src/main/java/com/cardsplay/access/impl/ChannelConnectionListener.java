
package com.cardsplay.access.impl;

import com.cardsplay.access.driver.CardsPlayProviderService;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * The listener class. Handles when the node disconnect.
 */
public class ChannelConnectionListener implements ChannelFutureListener {

    private final CardsPlayProviderService providerService;

    /**
     * Constructor from a OvsdbProviderService providerService.
     *
     * @param providerService the providerService to use
     */
    public ChannelConnectionListener(CardsPlayProviderService providerService) {
        this.providerService = providerService;
    }

    @Override
    public void operationComplete(ChannelFuture arg0) {
        providerService.nodeRemoved();
    }
}
