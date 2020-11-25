package com.github.beastyboo.stocks.domain.port;

import com.github.beastyboo.stocks.domain.entity.StockHolderEntity;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Torbie on 25.11.2020.
 */
public interface StockHolderRepository {

    void load();
    void close();
    Optional<StockHolderEntity> getStockHolder(UUID uuid);
    Set<StockHolderEntity> getAllStockHolders();

}
