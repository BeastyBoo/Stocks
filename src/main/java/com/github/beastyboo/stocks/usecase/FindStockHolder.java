package com.github.beastyboo.stocks.usecase;

import com.github.beastyboo.stocks.domain.entity.StockHolderEntity;
import com.github.beastyboo.stocks.domain.port.StockHolderRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Torbie on 25.11.2020.
 */
public class FindStockHolder {

    private final StockHolderRepository repository;

    public FindStockHolder(StockHolderRepository repository) {
        this.repository = repository;
    }

    public Optional<StockHolderEntity> getStockHolder(UUID uuid) {
        return repository.getStockHolder(uuid);
    }

    public Set<StockHolderEntity> getAllStockHolders() {
        return repository.getAllStockHolders();
    }

}
