
package com.cardsplay.access.api;

/**
 * Allows for providers interested in node events to be notified.
 */
public interface CardsPlayNodeListener {

    /**
     * Notifies that the node was added.
     *
     * @param nodeId the node where the event occurred
     */
    void nodeAdded(CardsPlayNodeId nodeId);

    /**
     * Notifies that the node was removed.
     *
     * @param nodeId the node where the event occurred
     */
    void nodeRemoved(CardsPlayNodeId nodeId);
}
