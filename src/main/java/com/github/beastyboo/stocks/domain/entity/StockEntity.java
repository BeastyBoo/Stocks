package com.github.beastyboo.stocks.domain.entity;

import yahoofinance.Stock;

import java.util.UUID;

/**
 * Created by Torbie on 25.11.2020.
 */
public class StockEntity {

    private final UUID stockOwner;
    private final Stock stock;
    private final double boughtPrice;
    private final int shareAmount;

    public static class Builder {
        private final UUID stockOwner;
        private final Stock stock;
        private final double boughtPrice;
        private int shareAmount = 1;

        public Builder(UUID stockOwner, Stock stock, double boughtPrice) {
            this.stockOwner = stockOwner;
            this.stock = stock;
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
        stockOwner = builder.stockOwner;
        stock = builder.stock;
        boughtPrice = builder.boughtPrice;
        shareAmount = builder.shareAmount;
    }

    public UUID getStockOwner() {
        return stockOwner;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockEntity that = (StockEntity) o;

        if (Double.compare(that.getBoughtPrice(), getBoughtPrice()) != 0) return false;
        if (getShareAmount() != that.getShareAmount()) return false;
        if (!getStockOwner().equals(that.getStockOwner())) return false;
        return getStock().equals(that.getStock());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getStockOwner().hashCode();
        result = 31 * result + getStock().hashCode();
        temp = Double.doubleToLongBits(getBoughtPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getShareAmount();
        return result;
    }

    @Override
    public String toString() {
        return "StockEntity{" +
                "stockOwner=" + stockOwner.toString() +
                ", stock=" + stock.getName() +
                ", boughtPrice=" + String.valueOf(boughtPrice) +
                ", shareAmount=" + String.valueOf(shareAmount) +
                '}';
    }
}
