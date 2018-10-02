
package com.cardsplay.core.api;

/**
 * Allows for providers interested in cards play events to be notified.
 */
public interface EventListener<E extends Event> {

    /**
     * Reacts to the specified event.
     *
     * @param event event to be processed
     */
    void event(E event);
}
