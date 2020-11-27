package com.github.beastyboo.stocks.usecase;

import com.github.beastyboo.stocks.adapter.type.StockType;
import com.github.beastyboo.stocks.domain.port.StockHolderRepository;
import yahoofinance.Stock;

import java.util.UUID;

/**
 * Created by Torbie on 27.11.2020.
 */
public class PurchaseStock {

    private final StockHolderRepository repository;

    public PurchaseStock(StockHolderRepository repository) {
        this.repository = repository;
    }

    public boolean purchaseStock(UUID holder, UUID stockUUID, Stock stock, StockType type, double boughtPrice, int shareAmount) {
        return repository.purchaseStock(holder, stockUUID, stock, type, boughtPrice, shareAmount);
    }


}
