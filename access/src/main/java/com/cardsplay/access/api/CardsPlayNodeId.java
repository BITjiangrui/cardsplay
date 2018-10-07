
package com.cardsplay.access.api;


import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * The class representing a nodeId of node which using ovsdb connection.
 * This class is immutable.
 */
public final class CardsPlayNodeId{
    private static final String SCHEME = "cardsplay";
    private  final UUID playerNodeId;

    /**
     * Creates a new node identifier from an IpAddress ipAddress, a long port.
     *
     * @param uuid node uuid
     */
    public CardsPlayNodeId(UUID uuid) {
        // TODO: port is currently not in use, need to remove it later
        checkNotNull(uuid, "uuid is  null");
        this.playerNodeId = uuid;
    }

    /**
     * Gets the value of the NodeId.
     *
     * @return the value of the NodeId.
     */
    public UUID nodeId() {
        return playerNodeId;
    }
}
