package com.cardsplay.core.models;

public class TableInfo {

    public TableId tableId;

    public Iterable<Player> players;

    public int seq;

    public Dealer dealer;

    public TableInfo(Builder builder){
        this.tableId = builder.tableId;
        this.players = builder.players;
        this.seq = builder.seq;
        this.dealer = builder.dealer;
    }
    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        public TableId tableId;

        public Iterable<Player> players;

        int seq;

        Dealer dealer;

        public Builder() {

        }

        public Builder tableId(TableId tableId){
            this.tableId = tableId;
            return this;
        }

        public Builder players(Iterable<Player> players){
            this.players = players;
            return this;
        }

        public Builder sequence(int seq){
            this.seq = seq;
            return this;
        }

        public Builder dealer(Dealer dealer){
            this.dealer = dealer;
            return this;
        }

        public TableInfo build() {
            return new TableInfo(this);
        }

    }
}
