
package com.cardsplay.access.api;


import static com.google.common.base.Preconditions.checkNotNull;

import com.cardsplay.util.IpAddress;


/**
 * The class representing a nodeId of node which using ovsdb connection.
 * This class is immutable.
 */
public final class CardsPlayNodeId{
    private static final String SCHEME = "cardsplay";
    private final String ipAddress;

    /**
     * Creates a new node identifier from an IpAddress ipAddress, a long port.
     *
     * @param ipAddress node IP address
     * @param port node port
     */
    public CardsPlayNodeId(IpAddress ipAddress, long port) {
        // TODO: port is currently not in use, need to remove it later
        checkNotNull(ipAddress, "ipAddress is not null");
        this.ipAddress = ipAddress.toString();
    }

    @Override
    public String toString() {
        return SCHEME + ":" + ipAddress;
    }

    /**
     * Gets the value of the NodeId.
     *
     * @return the value of the NodeId.
     */
    public String nodeId() {
        return SCHEME + ":" + ipAddress;
    }

    /**
     * Get the IP address of the node.
     *
     * @return the IP address of the node
     */
    public String getIpAddress() {
        return ipAddress;
    }
}
