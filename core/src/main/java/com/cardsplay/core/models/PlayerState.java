package com.cardsplay.core.models;

public enum PlayerState {

    Online(0), Offline(0), Ready(0), Playing(1), WatchCards(2), Discard(0);

    private final int times;

    private PlayerState(int times) {
        this.times = times;
    }

    public int getTimes() {
        return times;
    }
}
