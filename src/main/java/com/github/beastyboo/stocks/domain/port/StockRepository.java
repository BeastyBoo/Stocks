package com.github.beastyboo.stocks.domain.port;

import com.github.beastyboo.stocks.adapter.type.StockType;
import com.github.beastyboo.stocks.domain.entity.StockEntity;
import yahoofinance.Stock;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by Torbie on 25.11.2020.
 */
public interface StockRepository {

    void load();
    void close();
    Optional<StockEntity> getStock(UUID uuid);
    boolean createStockEntity(UUID stockUUID, Stock stock, StockType type, double boughtPrice, int shareAmount);

}
