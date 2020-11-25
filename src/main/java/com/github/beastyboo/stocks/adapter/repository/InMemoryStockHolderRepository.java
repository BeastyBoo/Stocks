package com.github.beastyboo.stocks.adapter.repository;

import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.entity.StockHolderEntity;
import com.github.beastyboo.stocks.domain.port.StockHolderRepository;

import java.util.*;

/**
 * Created by Torbie on 25.11.2020.
 */
public class InMemoryStockHolderRepository implements StockHolderRepository{

    private final Stocks core;
    private final Map<UUID, StockHolderEntity> stockHolderMemory;

    public InMemoryStockHolderRepository(Stocks core) {
        this.core = core;
        stockHolderMemory = new HashMap<>();
    }

    @Override
    public void load() {

    }

    @Override
    public void close() {

    }

    @Override
    public Optional<StockHolderEntity> getStockHolder(UUID uuid) {
        return Optional.ofNullable(stockHolderMemory.get(uuid));
    }

    @Override
    public Set<StockHolderEntity> getAllStockHolders() {
        return new HashSet<>(stockHolderMemory.values());
    }

    public Map<UUID, StockHolderEntity> getStockHolderMemory() {
        return stockHolderMemory;
    }
}
