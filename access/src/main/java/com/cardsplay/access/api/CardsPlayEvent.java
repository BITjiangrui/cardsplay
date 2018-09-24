
package com.cardsplay.access.api;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * The abstract event of CardsPlay.
 */
public final class CardsPlayEvent<S> {

    public enum Type {
        /**
         * Signifies that a new CardsPlay port update has been detected.
         */
        PORT_ADDED,
        /**
         * Signifies that a CardsPlay port has been removed.
         */
        PORT_REMOVED
    }

    private final Type type;
    private final S subject;

    /**
     * Creates an event of a given type and for the specified event subject.
     *
     * @param type event type
     * @param subject event subject
     */
    public CardsPlayEvent(Type type, S subject) {
        this.type = type;
        this.subject = subject;
    }

    /**
     * Returns the type of event.
     *
     * @return event type
     */
    public Type type() {
        return type;
    }

    /**
     * Returns the subject of event.
     *
     * @return subject to which this event pertains
     */
    public S subject() {
        return subject;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("type", type())
                .add("subject", subject()).toString();
    }

}
