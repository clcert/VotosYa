package com.ninjup.votosya.data.api.client;

import com.ninjup.votosya.data.model.Candidate;
import com.ninjup.votosya.data.model.Place;
import com.ninjup.votosya.data.model.Reporte;
import com.ninjup.votosya.data.model.Result;
import com.ninjup.votosya.data.model.Table;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Njara on 12-07-2017.
 */

public interface ServerService {

    Observable<List<Candidate>> getCandidates();
    Observable<List<Place>> getPlaces();
    Observable<List<Table>> getTables();
    Observable<Reporte> postReporte(Reporte body);
    Observable<Result> getResultados();
}
