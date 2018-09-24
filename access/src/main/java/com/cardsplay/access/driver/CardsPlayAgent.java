
package com.cardsplay.access.driver;


import com.cardsplay.access.api.CardsPlayClientService;
import com.cardsplay.access.api.CardsPlayNodeId;


/**
 * Responsible for keeping track of the current set of nodes connected to the
 * system.
 */
public interface CardsPlayAgent {
    /**
     * Add a node that has just connected to the system.
     *
     * @param nodeId the nodeId to add
     * @param cardsPlayClient the actual node object.
     */
    void addConnectedNode(CardsPlayNodeId nodeId, CardsPlayClientService  cardsPlayClient);

    /**
     * Clear all state in controller node maps for a node that has disconnected
     * from the local controller. Also release control for that node from the
     * global repository. Notify node listeners.
     *
     * @param nodeId the node id to be removed.
     */
    void removeConnectedNode(CardsPlayNodeId nodeId);
}
