package com.ninjup.votosya.presenter;

import android.util.Log;

import com.ninjup.votosya.data.model.Candidate;
import com.ninjup.votosya.data.model.Table;
import com.ninjup.votosya.interactor.TablesInteractor;

import java.util.List;

/**
 * Created by Njara on 12-07-2017.
 */

public class TablesPresenter extends Presenter<TablesPresenter.View> {

    private TablesInteractor interactor;

    public TablesPresenter(TablesInteractor interactor) {
        this.interactor = interactor;
    }

    public void getTables(List<Table> tables) {
        getView().showLoading();

        if (!tables.isEmpty() && tables.size() > 0) {
            getView().hideLoading();
            getView().renderTables(tables);
            Log.d("TAG", "si");
        } else {
            Log.d("TAG", "no");
            getView().showCandidateNotFoundMessage();
        }


        Log.d("TAG", "error");

    }


    public interface View extends Presenter.View {
        void showLoading();

        void hideLoading();

        void showCandidateNotFoundMessage();

        void showConnectionErrorMessage();

        void showServerError();

        void renderTables(List<Table> tables);

        void launchCandidateDetail(Candidate candidate);
    }
}
