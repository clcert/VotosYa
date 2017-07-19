package com.ninjup.votosya.data.api.retrofit.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.ninjup.votosya.data.api.Constants;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Njara on 12-07-2017.
 */

public class TablesDeserializer<T> implements ListDeserializer<T> {
    @Override
    public List<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement tableJsonArray = json.getAsJsonObject().get(Constants.Deserializer.TABLES);
        return new Gson().fromJson(tableJsonArray, typeOfT);
    }
}
