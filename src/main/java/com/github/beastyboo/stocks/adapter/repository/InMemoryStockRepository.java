package com.github.beastyboo.stocks.adapter.repository;

import com.github.beastyboo.stocks.adapter.type.StockAdapter;
import com.github.beastyboo.stocks.adapter.type.StockType;
import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.entity.StockEntity;
import com.github.beastyboo.stocks.domain.port.StockRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import yahoofinance.Stock;

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
        gson = this.createInstance();
        folder = new File(core.getPlugin().getDataFolder(), "stock");
        stockMemory = new HashMap<>();
    }

    @Override
    public void load() {
        if(!folder.exists()) {
            folder.mkdirs();
        }
        File[] directoryListing = folder.listFiles();
        if (directoryListing == null) {
            return;
        }
        for (File child : directoryListing) {
            String json = core.getFileUtil().loadContent(child);
            StockEntity entity = this.deserialize(json);

            stockMemory.put(entity.getUUID(), entity);
        }
    }

    @Override
    public void close() {
        for(StockEntity entity : stockMemory.values()) {
            File file = new File(folder, entity.getUUID().toString() + ".json");
            if(!folder.exists()) {
                folder.mkdirs();
            }
            String json = this.serialize(entity);
            core.getFileUtil().saveFile(file, json);
        }
    }

    @Override
    public void deleteStock(StockEntity stockEntity) {
        stockMemory.remove(stockEntity.getUUID(), stockEntity);
        File sourceFile = new File(folder, stockEntity.getUUID().toString() + ".json");
        if(sourceFile.exists()) {
            sourceFile.delete();
        }
    }

    @Override
    public Optional<StockEntity> getStock(UUID uuid) {
        return Optional.ofNullable(stockMemory.get(uuid));
    }

    @Override
    public boolean createStockEntity(UUID stockUUID, Stock stock, StockType type, double boughtPrice, int shareAmount) {
        Optional<StockEntity> entity = this.getStock(stockUUID);

        if(entity.isPresent()) {
            return false;
        }

        StockEntity newEntity = new StockEntity.Builder(stockUUID, stock, type, boughtPrice).shareAmount(shareAmount).build();
        stockMemory.put(stockUUID, newEntity);

        return true;
    }

    private Gson createInstance() {
        return new GsonBuilder().registerTypeAdapter(StockEntity.class, new StockAdapter())
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
    }

    private String serialize(StockEntity entity) {
        return this.gson.toJson(entity);
    }

    private StockEntity deserialize(String json) {
        return this.gson.fromJson(json, StockEntity.class);
    }

}
