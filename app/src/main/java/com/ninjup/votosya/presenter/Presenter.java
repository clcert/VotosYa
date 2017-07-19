package com.ninjup.votosya.presenter;

import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Njara on 12-07-2017.
 */

abstract class Presenter<T extends Presenter.View> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private T view;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public void initialize() {
    }

    public void terminate() {
        dispose();
    }

    void addDisposableObvserver(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    private void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public interface View {
        Context context();
    }
}
