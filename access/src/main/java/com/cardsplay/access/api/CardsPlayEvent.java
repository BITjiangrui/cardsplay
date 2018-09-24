/*
 * Copyright 2015-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
