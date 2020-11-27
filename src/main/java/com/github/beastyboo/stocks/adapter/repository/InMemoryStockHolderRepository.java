package com.github.beastyboo.stocks.adapter.repository;

import com.github.beastyboo.stocks.adapter.type.StockHolderAdapter;
import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.entity.StockHolderEntity;
import com.github.beastyboo.stocks.domain.port.StockHolderRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.*;

/**
 * Created by Torbie on 25.11.2020.
 */
public class InMemoryStockHolderRepository implements StockHolderRepository{

    private final Stocks core;
    private final Gson gson;
    private final File folder;
    private final Map<UUID, StockHolderEntity> stockHolderMemory;

    public InMemoryStockHolderRepository(Stocks core) {
        this.core = core;
        gson = this.createInstance();
        folder = new File(core.getPlugin().getDataFolder(), "stockholder");
        stockHolderMemory = new HashMap<>();
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
            StockHolderEntity entity = this.deserialize(json);

            stockHolderMemory.put(entity.getHolder(), entity);
        }
    }

    @Override
    public void close() {
        for(StockHolderEntity entity : stockHolderMemory.values()) {
            File file = new File(folder, entity.getHolder().toString() + ".json");
            if(!folder.exists()) {
                folder.mkdirs();
            }
            String json = this.serialize(entity);
            core.getFileUtil().saveFile(file, json);
        }
    }

    @Override
    public Optional<StockHolderEntity> getStockHolder(UUID uuid) {
        return Optional.ofNullable(stockHolderMemory.get(uuid));
    }

    @Override
    public Set<StockHolderEntity> getAllStockHolders() {
        return new HashSet<>(stockHolderMemory.values());
    }

    public Map<UUID, StockHolderEntity> getStockHolderMemory() {
        return stockHolderMemory;
    }

    private Gson createInstance() {
        return new GsonBuilder().registerTypeAdapter(StockHolderEntity.class, new StockHolderAdapter(core))
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
    }

    private String serialize(StockHolderEntity entity) {
        return this.gson.toJson(entity);
    }

    private StockHolderEntity deserialize(String json) {
        return this.gson.fromJson(json, StockHolderEntity.class);
    }

}
