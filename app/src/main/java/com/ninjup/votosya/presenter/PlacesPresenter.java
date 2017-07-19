package com.ninjup.votosya.presenter;

import android.util.Log;

import com.ninjup.votosya.data.model.Candidate;
import com.ninjup.votosya.data.model.Place;
import com.ninjup.votosya.interactor.PlacesInteractor;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Njara on 12-07-2017.
 */

public class PlacesPresenter extends Presenter<PlacesPresenter.View> {

    private PlacesInteractor interactor;

    public PlacesPresenter(PlacesInteractor interactor) {
        this.interactor = interactor;
    }

    public void getPlaces() {
        getView().showLoading();
        Disposable disposable = interactor.getPlaces().subscribe(places -> {
            if (!places.isEmpty() && places.size() > 0) {
                getView().hideLoading();
                getView().renderPlaces(places);
                Log.d("TAG", "si");
            } else {
                Log.d("TAG", "no");
                getView().showCandidateNotFoundMessage();
            }
        }, throwable -> {
            throwable.printStackTrace();
            getView().showConnectionErrorMessage();
            Log.d("TAG", "error");
        });
        addDisposableObvserver(disposable);
    }

    public void launchCandidateDetail(Candidate candidate) {
        getView().launchCandidateDetail(candidate);
    }

    public interface View extends Presenter.View {
        void showLoading();

        void hideLoading();

        void showCandidateNotFoundMessage();

        void showConnectionErrorMessage();

        void showServerError();

        void renderPlaces(List<Place> places);

        void launchCandidateDetail(Candidate candidate);
    }
}
