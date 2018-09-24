
package com.cardsplay.access.exception;

/**
 * This exception is thrown when the caller invoke the unsupported method or
 * use the encoding is not supported.
 */
public class UnsupportedException extends RuntimeException {
    private static final long serialVersionUID = 1377011546616825375L;

    /**
     * Constructs a UnsupportedException object.
     * @param message error message
     */
    public UnsupportedException(String message) {
        super(message);
    }

    /**
     * Constructs a UnsupportedException object.
     * @param message error message
     * @param cause Throwable
     */
    public UnsupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
