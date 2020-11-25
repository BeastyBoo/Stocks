package com.github.beastyboo.stocks.domain.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Torbie on 25.11.2020.
 */
public class StockHolderEntity {

    private final UUID holder;
    private final Set<StockEntity> stocks;
    private final double totalEarnings;

    public static class Builder {
        private final UUID holder;
        private Set<StockEntity> stocks = new HashSet<>();
        private double totalEarnings = 0;

        public Builder(UUID holder) {
            this.holder = holder;
        }

        public Builder stocks(Set<StockEntity> value) {
            stocks = value;
            return this;
        }

        public Builder totalEarnings(double value) {
            totalEarnings = value;
            return this;
        }

        public StockHolderEntity build() {
            return new StockHolderEntity(this);
        }
    }

    private StockHolderEntity(Builder builder) {
        holder = builder.holder;
        stocks = builder.stocks;
        totalEarnings = builder.totalEarnings;
    }

    public UUID getHolder() {
        return holder;
    }

    public Set<StockEntity> getStocks() {
        return stocks;
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockHolderEntity that = (StockHolderEntity) o;

        if (Double.compare(that.getTotalEarnings(), getTotalEarnings()) != 0) return false;
        if (!getHolder().equals(that.getHolder())) return false;
        return getStocks().equals(that.getStocks());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getHolder().hashCode();
        result = 31 * result + getStocks().hashCode();
        temp = Double.doubleToLongBits(getTotalEarnings());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "StockHolderEntity{" +
                "holder=" + holder.toString() +
                ", stocks=" + stocks.toString() +
                ", totalEarnings=" + String.valueOf(totalEarnings) +
                '}';
    }



}
