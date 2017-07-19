package com.ninjup.votosya.data.api.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ninjup.votosya.data.api.Constants;
import com.ninjup.votosya.data.api.retrofit.deserializer.CandidatesDeserializer;
import com.ninjup.votosya.data.api.retrofit.deserializer.TablesDeserializer;
import com.ninjup.votosya.data.model.Candidate;
import com.ninjup.votosya.data.model.Table;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Njara on 12-07-2017.
 */

public abstract class ServerRetrofitClient {

    private ServerRetrofitService serverRetrofitService;

    public ServerRetrofitClient() {
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofit = retrofitBuilder();
        serverRetrofitService = retrofit.create(getServerServiceClass());
    }

    public Retrofit retrofitBuilder() {
        return new Retrofit.Builder().baseUrl(Constants.VOTOSYA_API)
                .addConverterFactory(GsonConverterFactory.create(getServerDeserializer()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private Class<ServerRetrofitService> getServerServiceClass() {
        return ServerRetrofitService.class;
    }

    private Gson getServerDeserializer() {
        return new GsonBuilder().registerTypeAdapter(new TypeToken<List<Candidate>>() {
        }
                .getType(), new CandidatesDeserializer<Candidate>())
                .registerTypeAdapter(new TypeToken<List<Table>>() {
                }
                        .getType(), new TablesDeserializer<Table>())
                .create();
    }

    protected ServerRetrofitService getServerService() {
        return serverRetrofitService;
    }
}
