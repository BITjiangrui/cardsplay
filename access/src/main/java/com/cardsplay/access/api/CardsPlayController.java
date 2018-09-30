
package com.cardsplay.access.api;



import java.util.List;

import com.cardsplay.core.api.LifeCycleService;

/**
 * Abstraction of an CardsPlay controller. Serves as an one stop shop for obtaining
 * CardsPlay Node and (un)register listeners on CardsPlay events and CardsPlay node events.
 */
public interface CardsPlayController extends LifeCycleService{

    /**
     * Adds Node Event Listener.
     *
     * @param listener node listener
     */
    void addNodeListener(CardsPlayNodeListener listener);

    /**
     * Removes Node Event Listener.
     *
     * @param listener node listener
     */
    void removeNodeListener(CardsPlayNodeListener listener);

    /**
     * Gets all the nodes information.
     *
     * @return the list of node id
     */
    List<CardsPlayNodeId> getNodeIds();

    /**
     * Gets an CardsPlay client by node identifier.
     *
     * @param nodeId node identifier
     * @return CardsPlayClient cards play node information
     */
    CardsPlayClientService getCardsPlayClient(CardsPlayNodeId nodeId);
}
