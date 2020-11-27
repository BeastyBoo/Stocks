package com.github.beastyboo.stocks.usecase;

import com.github.beastyboo.stocks.domain.entity.StockEntity;
import com.github.beastyboo.stocks.domain.port.StockRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by Torbie on 25.11.2020.
 */
public class FindStock {

    private final StockRepository repository;

    public FindStock(StockRepository repository) {
        this.repository = repository;
    }

    public Optional<StockEntity> getStock(UUID uuid) {
        return repository.getStock(uuid);
    }
}
