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
package com.cards.access.driver;


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
