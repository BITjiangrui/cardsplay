
package com.cardsplay.access.impl;

import com.cardsplay.access.api.CardsPlayClientService;
import com.cardsplay.access.api.CardsPlayController;
import com.cardsplay.access.api.CardsPlayNodeId;
import com.cardsplay.access.api.CardsPlayNodeListener;
import com.cardsplay.access.driver.CardsPlayAgent;
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

    public  final static Logger log = LoggerFactory
            .getLogger(CardsPlayControllerImpl.class);

    protected ConcurrentHashMap<CardsPlayNodeId,  CardsPlayClientService> cardsPlayClients =
            new ConcurrentHashMap< CardsPlayNodeId,  CardsPlayClientService>();

    protected  CardsPlayAgent agent = new InternalCardsPlayNodeAgent();

    protected Set<CardsPlayNodeListener> cardsPlayNodeListener = new CopyOnWriteArraySet<>();

    protected ConcurrentHashMap<String, CardsPlayClientService> requestNotification =
            new ConcurrentHashMap<String, CardsPlayClientService>();

    protected ConcurrentHashMap<String, String> requestDbName = new ConcurrentHashMap<String, String>();

    private final Controller controller = new Controller();

    private static CardsPlayController instance = new CardsPlayControllerImpl();

    private CardsPlayControllerImpl(){

    }
    @Override
    public void activate() {
        controller.start(agent);
        log.info("CardsPlayController Started");
    }

    @Override
    public void deactivate() {
        controller.stop();
        log.info("CardsPlayController Stopped");
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
    public List<CardsPlayNodeId> getNodeIds() {
        return ImmutableList.copyOf(cardsPlayClients.keySet());
    }

    @Override
    public CardsPlayClientService getCardsPlayClient(CardsPlayNodeId nodeId) {
        return cardsPlayClients.get(nodeId);
    }

    public static CardsPlayController getInstance(){
        return instance;
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
