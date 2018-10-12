package com.cardsplay.core.models;

import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

public class Bet {
    private final double amount;

    public Bet(double amount){
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Bet) {
            final Bet other = (Bet) obj;
            return Objects.equals(this.amount, other.amount);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("amount", amount)
                .toString();
    }
}
