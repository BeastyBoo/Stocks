package com.github.beastyboo.stocks.adapter.type;

import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.entity.StockEntity;
import com.github.beastyboo.stocks.domain.entity.StockHolderEntity;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Torbie on 25.11.2020.
 */
public class StockHolderAdapter extends TypeAdapter<StockHolderEntity>{

    private final Stocks core;

    public StockHolderAdapter(Stocks core) {
        this.core = core;
    }

    @Override
    public void write(JsonWriter out, StockHolderEntity value) throws IOException {
        out.beginObject();

        out.name("holder").value(value.getHolder().toString());

        out.name("stocks").beginArray();
        for(StockEntity stockEntity : value.getStocks()) {
            out.beginObject();

            out.name("uuid").value(stockEntity.getUUID().toString());

            out.endObject();
        }
        out.endArray();

        out.name("total-earnings").value(value.getTotalEarnings());

        out.endObject();
    }

    @Override
    public StockHolderEntity read(JsonReader in) throws IOException {
        in.beginObject();

        UUID uuid = null;
        Set<StockEntity> stocks = new HashSet<>();
        double totalEarnings = 0;

        while (in.hasNext()) {
            switch (in.nextName()) {
                case "holder":
                    uuid = UUID.fromString(in.nextString());
                    break;
                case "stocks":
                    in.beginArray();
                    while (in.hasNext()) {
                        in.beginObject();

                        while(in.hasNext()) {
                            switch (in.nextName()) {
                                case "uuid":
                                    Optional<StockEntity> stockEntity = core.getStockConfig().getStock().getStock(UUID.fromString(in.nextString()));
                                    if(stockEntity.isPresent()) {
                                        stocks.add(stockEntity.get());
                                    }
                                    break;
                            }
                        }

                        in.endObject();
                    }
                    in.endArray();
                    break;
                case "total-earnings":
                    totalEarnings = in.nextDouble();
                    break;
            }
        }
        StockHolderEntity entity = new StockHolderEntity.Builder(uuid).stocks(stocks).totalEarnings(totalEarnings).build();
        in.endObject();
        return entity;
    }
}
