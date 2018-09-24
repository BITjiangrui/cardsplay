package com.cardsplay.util;


/**
 * Represents condition where an item is not found or not available.
 */
public class ItemNotFoundException extends RuntimeException {

    /**
     * Creates a new exception with no message.
     */
    public ItemNotFoundException() {
    }

    /**
     * Creates a new exception with the supplied message.
     * @param message error message
     */
    public ItemNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the supplied message and cause.
     * @param message error message
     * @param cause cause of the error
     */
    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
