package com.ninjup.votosya.data.api.retrofit;

import com.google.gson.JsonObject;
import com.ninjup.votosya.data.api.Constants;
import com.ninjup.votosya.data.model.Candidate;
import com.ninjup.votosya.data.model.Place;
import com.ninjup.votosya.data.model.Reporte;
import com.ninjup.votosya.data.model.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;

/**
 * Created by Njara on 12-07-2017.
 */

public interface ServerRetrofitService {


    @GET(Constants.Endpoint.CANDIDATE_SEARCH)
    Observable<List<Candidate>> searchCandidate();

    @GET(Constants.Endpoint.PLACE_SEARCH)
    Observable<List<Place>> getPlaces();

    @GET(Constants.Endpoint.RESULTADOS)
    Observable<Result> getResultados();

    @Headers("Content-Type: application/json")
    @HTTP(method = "POST", path = Constants.Endpoint.VOTES_REPORT, hasBody = true)
    Observable<Reporte> postReporte(@Body JsonObject body);

    @Headers("Content-Type: application/json")
    @HTTP(method = "POST", path = "/api/reporte/", hasBody = true)
    Call<JsonObject> postReporteOld(@Body JsonObject body);
}
