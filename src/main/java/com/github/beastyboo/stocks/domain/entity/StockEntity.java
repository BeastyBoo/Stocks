package com.github.beastyboo.stocks.domain.entity;

import com.github.beastyboo.stocks.adapter.type.StockType;
import yahoofinance.Stock;

import java.util.UUID;

/**
 * Created by Torbie on 25.11.2020.
 */
public class StockEntity {

    private final UUID uuid;
    private final Stock stock;
    private final StockType type;
    private final double boughtPrice;
    private final int shareAmount;

    public static class Builder {
        private final UUID uuid;
        private final Stock stock;
        private final StockType type;
        private final double boughtPrice;
        private int shareAmount = 1;

        public Builder(UUID uuid, Stock stock, StockType type, double boughtPrice) {
            this.uuid = uuid;
            this.stock = stock;
            this.type = type;
            this.boughtPrice = boughtPrice;
        }

        public Builder shareAmount(int value) {
            shareAmount = value;
            return this;
        }

        public StockEntity build() {
            return new StockEntity(this);
        }

    }

    private StockEntity(Builder builder) {
        uuid = builder.uuid;
        stock = builder.stock;
        type = builder.type;
        boughtPrice = builder.boughtPrice;
        shareAmount = builder.shareAmount;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Stock getStock() {
        return stock;
    }

    public double getBoughtPrice() {
        return boughtPrice;
    }

    public int getShareAmount() {
        return shareAmount;
    }

    public StockType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockEntity that = (StockEntity) o;

        if (Double.compare(that.getBoughtPrice(), getBoughtPrice()) != 0) return false;
        if (getShareAmount() != that.getShareAmount()) return false;
        if (!getUUID().equals(that.getUUID())) return false;
        return getStock().equals(that.getStock());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getUUID().hashCode();
        result = 31 * result + getStock().hashCode();
        temp = Double.doubleToLongBits(getBoughtPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getShareAmount();
        return result;
    }

    @Override
    public String toString() {
        return "StockEntity{" +
                "stockOwner=" + uuid.toString() +
                ", stock=" + stock.getName() +
                ", boughtPrice=" + String.valueOf(boughtPrice) +
                ", shareAmount=" + String.valueOf(shareAmount) +
                '}';
    }
}
