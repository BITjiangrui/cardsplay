package com.cardsplay.core.api;

import com.cardsplay.core.models.Player;
import org.joda.time.LocalDateTime;

import static com.google.common.base.MoreObjects.toStringHelper;

public class PlayerEvent extends AbstractEvent<PlayerEvent.Type, Player> {

    /**
     * Type of table update events.
     */
    public enum Type {
        PLAYER_ONLINE, PLAYER_OFFLINE, PLAYER_READY, PLAYER_UNDOREADY;
    }

    private Player prevSubject;

    public PlayerEvent(Type type, Player subject) {
        super(type, subject);
    }

    public PlayerEvent(Type type, Player player, long time) {
        super(type, player, time);
    }

    public PlayerEvent(Type type, Player player, Player prevSubject) {
        super(type, player);
        this.prevSubject = prevSubject;
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
