package com.ninjup.votosya.interactor;

import com.ninjup.votosya.data.api.client.ServerService;
import com.ninjup.votosya.data.model.Place;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Njara on 12-07-2017.
 */

public class PlacesInteractor {

    private ServerService serverService;

    public PlacesInteractor(ServerService serverService) {
        this.serverService = serverService;
    }

    public Observable<List<Place>> getPlaces() {
        return serverService.getPlaces();
    }
}
