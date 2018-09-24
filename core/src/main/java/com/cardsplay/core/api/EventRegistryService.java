package com.cardsplay.core.api;

public interface EventRegistryService {
    /**
     * Adds event listener.
     *
     * @param listener event listener
     */
    void addEventListener(EventListener listener);

    /**
     * Removes event listener.
     *
     * @param listener event listener
     */
    void removeEventListener(EventListener listener);
}
