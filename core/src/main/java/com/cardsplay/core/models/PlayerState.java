package com.cardsplay.core.models;

public enum PlayerState {
    /*
     * Online:在线上
     * Offline:离线的状态
     * Ready:进入牌桌已准备
     * Playing:游戏进行中
     * Discard:弃牌状态
     * WatchCards:看牌
     * */
    Online(0), Offline(0), Ready(0), Playing(1), WatchCards(2), Discard(0);

    private final int times;

    private PlayerState(int times) {
        this.times = times;
    }

    public int getTimes() {
        return times;
    }
}
