package com.github.beastyboo.stocks.config;

import com.github.beastyboo.stocks.adapter.repository.InMemoryStockRepository;
import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.port.StockRepository;
import com.github.beastyboo.stocks.usecase.CreateStockEntity;
import com.github.beastyboo.stocks.usecase.FindStock;

/**
 * Created by Torbie on 25.11.2020.
 */
public class StockConfig {

    private final Stocks core;
    private final StockRepository repository;
    private final FindStock stock;
    private final CreateStockEntity createStockEntity;

    public StockConfig(Stocks core) {
        this.core = core;
        repository = new InMemoryStockRepository(core);
        stock = new FindStock(repository);
        createStockEntity = new CreateStockEntity(repository);
    }

    public FindStock getStock() {
        return stock;
    }

    public CreateStockEntity getCreateStockEntity() {
        return createStockEntity;
    }

    public void load() {
        repository.load();
    }

    public void close() {
        repository.close();
    }
}
