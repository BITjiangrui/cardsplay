package com.cardsplay.core.api;

import com.cardsplay.core.models.Table;
import org.joda.time.LocalDateTime;

import static com.google.common.base.MoreObjects.toStringHelper;

public class TableEvent extends AbstractEvent<TableEvent.Type, Table>{

    /**
     * Type of table update events.
     */
    public enum Type {
        TABLE_UPDATE;
    }

    private Table prevSubject;

    public TableEvent(Type type, Table subject) {
        super(type, subject);
    }

    public TableEvent(Type type, Table port, long time) {
        super(type, port, time);
    }

    public TableEvent(Type type, Table port, Table prevSubject) {
        super(type, port);
        if (type == Type.TABLE_UPDATE) {
            this.prevSubject = prevSubject;
        }
    }

    public Table prevSubject() {
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
