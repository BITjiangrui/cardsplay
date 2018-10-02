
package com.cardsplay.core.api;

/**
 * Abstraction of an of a time-stamped event pertaining to an arbitrary subject.
 */
public interface Event<T extends Enum, S> {

    /**
     * Returns the timestamp of when the event occurred, given in milliseconds
     * since the start of epoch.
     *
     * @return timestamp in milliseconds
     */
    long time();

    /**
     * Returns the type of the event.
     *
     * @return event type
     */
    T type();

    /**
     * Returns the subject of the event.
     *
     * @return subject to which this event pertains
     */
    S subject();

}
