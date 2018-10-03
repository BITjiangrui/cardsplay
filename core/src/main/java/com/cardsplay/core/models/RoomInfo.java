package com.cardsplay.core.models;

public class RoomInfo {

    public RoomId roomId;

    public Iterable<TableInfo> tablesInfo;

    public String nickName;

    public int seq;

    public RoomInfo(Builder builder){
        this.roomId = builder.roomId;
        this.tablesInfo = builder.tablesInfo;
        this.nickName = builder.nickName;
        this.seq = builder.seq;
    }
    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        RoomId roomId;

        Iterable<TableInfo> tablesInfo;

        String nickName;

        int seq;

        public Builder() {

        }

        public Builder roomId(RoomId roomId){
            this.roomId = roomId;
            return this;
        }

        public Builder tablesInfo(Iterable<TableInfo> tablesInfo){
            this.tablesInfo = tablesInfo;
            return this;
        }

        public Builder nickName(String nickName){
            this.nickName = nickName;
            return this;
        }

        public Builder sequence(int seq){
            this.seq = seq;
            return this;
        }

        public RoomInfo build() {
            return new RoomInfo(this);
        }

    }
}
