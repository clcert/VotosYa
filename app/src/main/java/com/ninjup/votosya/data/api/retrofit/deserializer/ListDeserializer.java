package com.ninjup.votosya.data.api.retrofit.deserializer;

import com.google.gson.JsonDeserializer;

import java.util.List;

/**
 * Created by Njara on 12-07-2017.
 */

public interface ListDeserializer<T> extends JsonDeserializer<List<T>>{
}
