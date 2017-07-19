package com.ninjup.votosya.interactor;

import com.ninjup.votosya.data.api.client.ServerService;
import com.ninjup.votosya.data.model.Result;

import io.reactivex.Observable;

/**
 * Created by Njara on 13-07-2017.
 */

public class ResultInteractor {

    private ServerService serverService;

    public ResultInteractor(ServerService serverService) {
        this.serverService = serverService;
    }

    public Observable<Result> getResultados() {
        return serverService.getResultados();
    }
}
