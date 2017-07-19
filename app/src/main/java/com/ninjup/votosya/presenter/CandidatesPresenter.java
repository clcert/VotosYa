package com.ninjup.votosya.presenter;

import android.util.Log;

import com.ninjup.votosya.data.model.Candidate;
import com.ninjup.votosya.interactor.CandidatesInteractor;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Njara on 12-07-2017.
 */

public class CandidatesPresenter extends Presenter<CandidatesPresenter.View> {

    private CandidatesInteractor interactor;

    public CandidatesPresenter(CandidatesInteractor interactor) {
        this.interactor = interactor;
    }

    public void onSearchCandidates() {
        getView().showLoading();
        Disposable disposable = interactor.searchCandidates().subscribe(candidates -> {
            if (!candidates.isEmpty() && candidates.size() > 0) {
                getView().hideLoading();
                getView().renderCandidates(candidates);
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

        void renderCandidates(List<Candidate> candidates);
    //    void updateView(List<Candidate> candidates);

        void launchCandidateDetail(Candidate candidate);
    }
}
