package com.github.beastyboo.stocks.adapter.repository;

import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.entity.StockEntity;
import com.github.beastyboo.stocks.domain.port.StockRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Torbie on 25.11.2020.
 */
public class InMemoryStockRepository implements StockRepository{

    private final Stocks core;
    private final Map<UUID, StockEntity> stockMemory;

    public InMemoryStockRepository(Stocks core) {
        this.core = core;
        stockMemory = new HashMap<>();
    }

    @Override
    public void load() {

    }

    @Override
    public void close() {

    }

    @Override
    public Optional<StockEntity> getStock(UUID uuid) {
        return Optional.ofNullable(stockMemory.get(uuid));
    }
}
