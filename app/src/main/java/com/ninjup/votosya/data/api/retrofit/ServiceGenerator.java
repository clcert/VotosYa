package com.ninjup.votosya.data.api.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ninjup.votosya.data.api.Constants.VOTOSYA_API;

/**
 * Created by Njara on 13-07-2017.
 */

public class ServiceGenerator {


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(VOTOSYA_API)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createServiceLogin(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(baseUrl + "/")
                        .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(httpClient.build()).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(serviceClass);
    }
}