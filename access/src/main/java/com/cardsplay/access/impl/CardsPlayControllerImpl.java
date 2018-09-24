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

import com.cards.access.driver.CardsPlayAgent;
import com.cardsplay.access.api.CardsPlayClientService;
import com.cardsplay.access.api.CardsPlayController;
import com.cardsplay.access.api.CardsPlayEventListener;
import com.cardsplay.access.api.CardsPlayNodeId;
import com.cardsplay.access.api.CardsPlayNodeListener;
import com.google.common.collect.ImmutableList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * The implementation of OvsdbController.
 */
public class CardsPlayControllerImpl implements CardsPlayController {

    public static final Logger log = LoggerFactory
            .getLogger(CardsPlayControllerImpl.class);

    protected ConcurrentHashMap<CardsPlayNodeId,  CardsPlayClientService> cardsPlayClients =
            new ConcurrentHashMap< CardsPlayNodeId,  CardsPlayClientService>();

    protected  CardsPlayAgent agent = new InternalCardsPlayNodeAgent();

    protected Set<CardsPlayNodeListener> cardsPlayNodeListener = new CopyOnWriteArraySet<>();
    protected Set<CardsPlayEventListener> cardsPlayEventListener = new CopyOnWriteArraySet<>();

    protected ConcurrentHashMap<String, CardsPlayClientService> requestNotification =
            new ConcurrentHashMap<String, CardsPlayClientService>();

    protected ConcurrentHashMap<String, String> requestDbName = new ConcurrentHashMap<String, String>();

    private final Controller controller = new Controller();

    public void init() {
        controller.start(agent);
        log.info("Started");
    }

    public void stop() {
        controller.stop();
        log.info("Stoped");
    }


    @Override
    public void addNodeListener(CardsPlayNodeListener listener) {
        if (!cardsPlayNodeListener.contains(listener)) {
            this.cardsPlayNodeListener.add(listener);
        }        
    }

    @Override
    public void removeNodeListener(CardsPlayNodeListener listener) {
        this.cardsPlayNodeListener.remove(listener);
    }

    @Override
    public void addCardsPlayEventListener(CardsPlayEventListener listener) {
        if (!cardsPlayEventListener.contains(listener)) {
            this.cardsPlayEventListener.add(listener);
        }
    }

    @Override
    public void removeCardsPlayEventListener(CardsPlayEventListener listener) {
        this.cardsPlayEventListener.remove(listener);
    }

    @Override
    public List<CardsPlayNodeId> getNodeIds() {
        return ImmutableList.copyOf(cardsPlayClients.keySet());
    }

    @Override
    public CardsPlayClientService getCardsPlayClient(CardsPlayNodeId nodeId) {
        return cardsPlayClients.get(nodeId);
    }


    /**
     * Implementation of an CardsPlay Agent which is responsible for keeping track
     * of connected node and the state in which they are.
     */
    private class InternalCardsPlayNodeAgent implements  CardsPlayAgent {
        
        @Override
        public void addConnectedNode(CardsPlayNodeId nodeId,
                                     CardsPlayClientService  cardsPlayClient) {

            if (cardsPlayClients.get(nodeId) != null) {
                return;
            } else {
                cardsPlayClients.put(nodeId, cardsPlayClient);
                for (CardsPlayNodeListener l : cardsPlayNodeListener) {
                    l.nodeAdded(nodeId);
                }
                return;
            }
        }

        @Override
        public void removeConnectedNode(CardsPlayNodeId nodeId) {
            cardsPlayClients.remove(nodeId);
            log.debug("Node connection is removed");
            for (CardsPlayNodeListener l : cardsPlayNodeListener) {
                l.nodeRemoved(nodeId);
            }
        }
    }
}
