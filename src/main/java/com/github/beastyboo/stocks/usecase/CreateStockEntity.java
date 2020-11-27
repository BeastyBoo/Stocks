package com.github.beastyboo.stocks.usecase;

import com.github.beastyboo.stocks.adapter.type.StockType;
import com.github.beastyboo.stocks.domain.entity.StockEntity;
import com.github.beastyboo.stocks.domain.port.StockRepository;
import yahoofinance.Stock;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by Torbie on 25.11.2020.
 */
public class CreateStockEntity {

    private final StockRepository repository;

    public CreateStockEntity(StockRepository repository) {
        this.repository = repository;
    }

    public boolean createStockEntity(UUID stockUUID, Stock stock, StockType type, double boughtPrice, int shareAmount) {
        return repository.createStockEntity(stockUUID, stock, type, boughtPrice, shareAmount);
    }
}
