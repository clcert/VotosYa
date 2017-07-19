package com.ninjup.votosya.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ninjup.votosya.R;
import com.ninjup.votosya.interactor.SplashInteractor;
import com.ninjup.votosya.presenter.SplashPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashPresenter.View {
    private SplashPresenter presenter;

    @BindView(R.id.iv_logo)
    ImageView iv_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        presenter = new SplashPresenter(new SplashInteractor(0));
        presenter.setView(this);
        presenter.onLoad();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLoadig(presenter.getTime());
    }

    @Override
    public void initLoading() {

    }

    @Override
    public void showLoadig(long time) {

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Animation anim =
                    AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
            anim.setFillEnabled(true);
            anim.setFillAfter(true);
            iv_logo.startAnimation(anim);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation arg0) {
                }

                @Override
                public void onAnimationRepeat(Animation arg0) {
                }

                @Override
                public void onAnimationEnd(Animation arg0) {
                    presenter.showMenuActivity();
                }
            });
        }, time);

    }

    @Override
    public void onDestroy() {
        presenter.terminate();
        super.onDestroy();
    }

    @Override
    public void showMenuActivity() {
        Intent intent = new Intent(context(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showServerError() {

    }

    @Override
    public Context context() {
        return SplashActivity.this;
    }
}
