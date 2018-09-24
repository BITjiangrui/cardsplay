
package com.cardsplay.core.api;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * The abstract event.
 */
public final class Event<S> {

    public enum Type {
        /**
         * Signifies that a new Player is ready or undo ready.
         */
        PLAYER_UPDATE,
        /**
         * Signifies that a the table is ready to start.
         */
        TABLE_UPDATE
    }

    private final Type type;
    private final S subject;

    /**
     * Creates an event of a given type and for the specified event subject.
     *
     * @param type event type
     * @param subject event subject
     */
    public Event(Type type, S subject) {
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
