package com.github.beastyboo.stocks.adapter.type;

import com.github.beastyboo.stocks.domain.entity.StockEntity;
import com.github.beastyboo.stocks.domain.entity.StockHolderEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Torbie on 27.11.2020.
 */
public class StockInventory implements InventoryHolder{

    private final StockHolderEntity stockHolderEntity;
    private final Map<Integer, StockEntity> stocksBySlot;
    private final UUID target;

    public StockInventory(StockHolderEntity stockHolderEntity, Map<Integer, StockEntity> stocksBySlot, UUID target) {
        this.stockHolderEntity = stockHolderEntity;
        this.stocksBySlot = stocksBySlot;
        this.target = target;
    }

    public StockHolderEntity getStockHolderEntity() {
        return stockHolderEntity;
    }

    public Map<Integer, StockEntity> getStocksBySlot() {
        return stocksBySlot;
    }

    public UUID getTarget() {
        return target;
    }

    @Override
    public Inventory getInventory() {
        return this.getInventory();
    }
}
