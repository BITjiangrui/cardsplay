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

/**
 * Abstraction of an CardsPlay controller. Serves as an one stop shop for obtaining
 * CardsPlay Node and (un)register listeners on CardsPlay events and CardsPlay node events.
 */
public interface CardsPlayController {

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
     * Adds ovsdb event listener.
     *
     * @param listener event listener
     */
    void addCardsPlayEventListener(CardsPlayEventListener listener);

    /**
     * Removes CardsPlay event listener.
     *
     * @param listener event listener
     */
    void removeCardsPlayEventListener(CardsPlayEventListener listener);

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
