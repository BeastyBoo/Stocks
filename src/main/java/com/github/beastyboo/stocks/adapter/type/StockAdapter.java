package com.github.beastyboo.stocks.adapter.type;

import com.github.beastyboo.stocks.domain.entity.StockEntity;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Torbie on 25.11.2020.
 */
public class StockAdapter extends TypeAdapter<StockEntity>{

    @Override
    public void write(JsonWriter out, StockEntity value) throws IOException {
        out.beginObject();
        out.name("uuid").value(value.getUUID().toString());
        out.name("stock").value(value.getStock().getSymbol());
        out.name("type").value(value.getType().toString());
        out.name("bought-price").value(value.getBoughtPrice());
        out.name("share-amount").value(value.getShareAmount());
        out.endObject();
    }

    @Override
    public StockEntity read(JsonReader in) throws IOException {
        in.beginObject();
        UUID uuid = null;
        Stock stock = null;
        StockType type = null;
        double boughtPrice = 0;
        int shareAmount = 0;

        while (in.hasNext()) {
            switch (in.nextName()) {
                case "uuid":
                    uuid = UUID.fromString(in.nextString());
                    break;
                case "stock":
                    stock = YahooFinance.get(in.nextString());
                    break;
                case "type":
                    type = Enum.valueOf(StockType.class, in.nextString());
                    break;
                case "bought-price":
                    boughtPrice = in.nextDouble();
                    break;
                case "share-amount":
                    shareAmount = in.nextInt();
                    break;
            }
        }

        StockEntity entity = new StockEntity.Builder(uuid, stock, type, boughtPrice).shareAmount(shareAmount).build();
        in.endObject();
        return entity;
    }
}
