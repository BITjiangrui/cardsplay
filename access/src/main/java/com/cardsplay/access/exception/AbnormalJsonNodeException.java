
package com.cardsplay.access.exception;

/**
 * AbnormalJsonNodeException exception is thrown when the received JsonNode is invalid.
 */
public class AbnormalJsonNodeException extends RuntimeException {
    private static final long serialVersionUID = 8328377718334680368L;

    /**
     * Constructs a AbnormalJsonNodeException object.
     * @param message error message
     */
    public AbnormalJsonNodeException(String message) {
        super(message);
    }

    /**
     * Constructs a AbnormalJsonNodeException object.
     * @param message error message
     * @param cause Throwable
     */
    public AbnormalJsonNodeException(String message, Throwable cause) {
        super(message, cause);
    }

}
