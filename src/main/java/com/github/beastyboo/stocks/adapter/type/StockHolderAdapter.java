package com.github.beastyboo.stocks.adapter.type;

import com.github.beastyboo.stocks.domain.entity.StockHolderEntity;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Torbie on 25.11.2020.
 */
public class StockHolderAdapter extends TypeAdapter<StockHolderEntity>{


    @Override
    public void write(JsonWriter out, StockHolderEntity value) throws IOException {

    }

    @Override
    public StockHolderEntity read(JsonReader in) throws IOException {
        return null;
    }
}
