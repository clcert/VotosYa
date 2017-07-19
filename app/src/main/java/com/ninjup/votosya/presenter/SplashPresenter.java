package com.ninjup.votosya.presenter;

import com.ninjup.votosya.interactor.SplashInteractor;

/**
 * Created by Njara on 10-06-2017.
 */

public class SplashPresenter extends Presenter<SplashPresenter.View> {
    private SplashInteractor interactor;

    public SplashPresenter(SplashInteractor interactor) {
        this.interactor = interactor;
    }

    public void onLoad() {
        getView().initLoading();
    }

    public void showMenuActivity() {
        getView().showMenuActivity();
    }

    public long getTime() {
        return interactor.getTime();
    }

    @Override
    public void terminate() {
        super.terminate();
        setView(null);
    }

    public interface View extends Presenter.View {

        void initLoading();

        void showLoadig(long time);

        void showMenuActivity();

        void showServerError();

    }
}
