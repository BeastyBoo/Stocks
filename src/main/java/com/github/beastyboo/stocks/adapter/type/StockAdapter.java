package com.github.beastyboo.stocks.adapter.type;

import com.github.beastyboo.stocks.domain.entity.StockEntity;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Torbie on 25.11.2020.
 */
public class StockAdapter extends TypeAdapter<StockEntity>{


    @Override
    public void write(JsonWriter out, StockEntity value) throws IOException {

    }

    @Override
    public StockEntity read(JsonReader in) throws IOException {
        return null;
    }
}
