package com.cardsplay.core.api;

import com.cardsplay.core.models.Player;
import org.joda.time.LocalDateTime;

import static com.google.common.base.MoreObjects.toStringHelper;

public class PlayerEvent extends AbstractEvent<PlayerEvent.Type, Player> {

    /**
     * Type of table update events.
     */
    public enum Type {
        PLAYER_UPDATE;
    }

    private Player prevSubject;

    public PlayerEvent(Type type, Player subject) {
        super(type, subject);
    }

    public PlayerEvent(Type type, Player port, long time) {
        super(type, port, time);
    }

    public PlayerEvent(Type type, Player port, Player prevSubject) {
        super(type, port);
        if (type == Type.PLAYER_UPDATE) {
            this.prevSubject = prevSubject;
        }
    }

    public Player prevSubject() {
        return this.prevSubject;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("time", new LocalDateTime(time()))
                .add("type", type())
                .add("subject", subject())
                .add("prevSubject", prevSubject())
                .toString();
    }
}
