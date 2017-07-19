package com.ninjup.votosya.data.api.client;

import android.util.Log;

import com.ninjup.votosya.data.api.retrofit.ServerRetrofitClient;
import com.ninjup.votosya.data.model.Candidate;
import com.ninjup.votosya.data.model.Place;
import com.ninjup.votosya.data.model.Reporte;
import com.ninjup.votosya.data.model.Result;
import com.ninjup.votosya.data.model.Table;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Njara on 12-07-2017.
 */

public class ServerClient extends ServerRetrofitClient implements ServerService {
    @Override
    public Observable<List<Candidate>> getCandidates() {
        return getServerService().searchCandidate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Place>> getPlaces() {
        return getServerService().getPlaces()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Table>> getTables() {
        return null;
    }

    @Override
    public Observable<Reporte> postReporte(Reporte reporte) {
        Log.d("TAG","JSON: "+reporte.toJson().toString());
        return getServerService().postReporte(reporte.toJson()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Result> getResultados() {
        return getServerService().getResultados().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
