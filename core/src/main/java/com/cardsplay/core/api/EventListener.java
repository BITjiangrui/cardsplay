
package com.cardsplay.core.api;

/**
 * Allows for providers interested in cards play events to be notified.
 */
public interface EventListener {
    /**
     * Handles the event.
     *
     * @param event event
     */
    void handle(Event<EventSubject> event);
}
