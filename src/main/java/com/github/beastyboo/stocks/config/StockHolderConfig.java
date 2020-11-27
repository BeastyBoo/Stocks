package com.github.beastyboo.stocks.config;

import com.github.beastyboo.stocks.adapter.repository.InMemoryStockHolderRepository;
import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.port.StockHolderRepository;
import com.github.beastyboo.stocks.usecase.FindStockHolder;

/**
 * Created by Torbie on 25.11.2020.
 */
public class StockHolderConfig {

    private final Stocks core;
    private final StockHolderRepository repository;
    private final FindStockHolder stockHolders;

    public StockHolderConfig(Stocks core) {
        this.core = core;
        repository = new InMemoryStockHolderRepository(core);
        stockHolders = new FindStockHolder(repository);
    }

    public FindStockHolder getStockHolders() {
        return stockHolders;
    }

    public void load() {
        repository.load();
    }

    public void close() {
        repository.close();
    }




}
