package com.github.beastyboo.stocks.config;

import com.github.beastyboo.stocks.adapter.repository.InMemoryStockHolderRepository;
import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.port.StockHolderRepository;

/**
 * Created by Torbie on 25.11.2020.
 */
public class StockHolderConfig {

    private final Stocks core;
    private final StockHolderRepository repository;

    public StockHolderConfig(Stocks core) {
        this.core = core;
        repository = new InMemoryStockHolderRepository(core);
    }


}
