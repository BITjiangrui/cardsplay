
package com.cardsplay.access.api;

/**
 * Allows for providers interested in w3c events to be notified.
 */
public interface CardsPlayEventListener {
    /**
     * Handles the w3c event.
     *
     * @param event w3c event
     */
    void handle(CardsPlayEvent<EventSubject> event);
}
