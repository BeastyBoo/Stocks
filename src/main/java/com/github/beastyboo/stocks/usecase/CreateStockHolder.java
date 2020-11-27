package com.github.beastyboo.stocks.usecase;

import com.github.beastyboo.stocks.domain.port.StockHolderRepository;

import java.util.UUID;

/**
 * Created by Torbie on 27.11.2020.
 */
public class CreateStockHolder {

    private final StockHolderRepository repository;

    public CreateStockHolder(StockHolderRepository repository) {
        this.repository = repository;
    }

    public boolean createStockHolder(UUID holder) {
        return repository.createStockHolder(holder);
    }

}
