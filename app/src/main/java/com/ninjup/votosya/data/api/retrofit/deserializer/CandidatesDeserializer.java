package com.ninjup.votosya.data.api.retrofit.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Njara on 12-07-2017.
 */

public class CandidatesDeserializer<T> implements ListDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
      //  JsonObject candidateJsonObject = json.getAsJsonObject().get(Constants.Deserializer.CANDIDATES).getAsJsonObject();
      //  JsonElement candidateJsonArray = candidateJsonObject.getAsJsonArray(Constants.Deserializer.ITEMS);
        JsonElement candidateJsonArray = json.getAsJsonArray();
        return new Gson().fromJson(candidateJsonArray, typeOfT);
    }
}
