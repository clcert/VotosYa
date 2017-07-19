package com.ninjup.votosya.presenter;

import android.util.Log;

import com.ninjup.votosya.data.model.Candidate;
import com.ninjup.votosya.data.model.Result;
import com.ninjup.votosya.interactor.ResultInteractor;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Njara on 13-07-2017.
 */

public class ResultPresenter extends Presenter<ResultPresenter.View> {

    private ResultInteractor interactor;

    public ResultPresenter(ResultInteractor interactor) {
        this.interactor = interactor;
    }

    public void onSearchCandidates() {
        getView().showLoading();
        Log.d("TAG", "RESULTADOS");
        Disposable disposable = interactor.getResultados().subscribe(candidates -> {
            if (!candidates.candidatos.isEmpty() && candidates.candidatos.size() > 0) {
                getView().hideLoading();
                getView().renderCandidates(candidates.candidatos);
                getView().renderResult(candidates);
                Log.d("TAG", "RESULTADOS");
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

        void renderResult(Result result);

        void showCandidateNotFoundMessage();

        void showConnectionErrorMessage();

        void showServerError();

        void renderCandidates(List<Candidate> candidates);
        //    void updateView(List<Candidate> candidates);

        void launchCandidateDetail(Candidate candidate);
    }
}
