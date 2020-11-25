package com.github.beastyboo.stocks.adapter.repository;

import com.github.beastyboo.stocks.adapter.type.StockAdapter;
import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.entity.StockEntity;
import com.github.beastyboo.stocks.domain.port.StockRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Torbie on 25.11.2020.
 */
public class InMemoryStockRepository implements StockRepository{

    private final Stocks core;
    private final Gson gson;
    private final File folder;
    private final Map<UUID, StockEntity> stockMemory;

    public InMemoryStockRepository(Stocks core) {
        this.core = core;
        gson = this.getGson();
        folder = new File(core.getPlugin().getDataFolder(), "stock");
        stockMemory = new HashMap<>();
    }

    @Override
    public void load() {

    }

    @Override
    public void close() {

    }

    @Override
    public Optional<StockEntity> getStock(UUID uuid) {
        return Optional.ofNullable(stockMemory.get(uuid));
    }

    private Gson getGson() {
        return new GsonBuilder().registerTypeAdapter(StockEntity.class, new StockAdapter())
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
    }

}
