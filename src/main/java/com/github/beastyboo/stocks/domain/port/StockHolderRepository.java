package com.github.beastyboo.stocks.domain.port;

import com.github.beastyboo.stocks.adapter.type.StockType;
import com.github.beastyboo.stocks.domain.entity.StockHolderEntity;
import org.bukkit.entity.Player;
import yahoofinance.Stock;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Torbie on 25.11.2020.
 */
public interface StockHolderRepository {

    void load();
    void close();
    void showProfile(Player target, Player player);
    Optional<StockHolderEntity> getStockHolder(UUID uuid);
    Set<StockHolderEntity> getAllStockHolders();
    boolean createStockHolder(UUID holder);
    boolean purchaseStock(UUID holder, UUID stockUUID, Stock stock, StockType type, double boughtPrice, int shareAmount);


}
