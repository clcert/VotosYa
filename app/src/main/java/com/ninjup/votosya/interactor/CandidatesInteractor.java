package com.ninjup.votosya.interactor;

import com.ninjup.votosya.data.api.client.ServerService;
import com.ninjup.votosya.data.model.Candidate;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Njara on 12-07-2017.
 */

public class CandidatesInteractor {

    private ServerService serverService;

    public CandidatesInteractor(ServerService serverService) {
        this.serverService = serverService;
    }

    public Observable<List<Candidate>> searchCandidates() {
        return serverService.getCandidates();
    }
}
