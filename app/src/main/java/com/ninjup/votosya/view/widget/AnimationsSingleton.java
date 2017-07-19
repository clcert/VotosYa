package com.ninjup.votosya.view.widget;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Njara on 11-06-2017.
 */

public class AnimationsSingleton {
    private static AnimationsSingleton instance = null;

    public static AnimationsSingleton getInstance() {
        if (instance == null) {
            instance = new AnimationsSingleton();
        }
        return instance;
    }

    public RotateAnimation getRotate() {
        return new RotateAnimation(0.0f, 1080.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
    }

    public ScaleAnimation getScale() {
        return new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
    }

    public TranslateAnimation getTranslate(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        return new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
    }

    public AlphaAnimation getAlpha() {
        return new AlphaAnimation(0.0f, 1.0f);
    }

    public AnimationSet btnRotate(long time) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(getRotate());
        animationSet.addAnimation(getScale());
        animationSet.addAnimation(getAlpha());
        animationSet.setDuration(time);
        return animationSet;
    }

    public AnimationSet letterTranslate(long time, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(getTranslate(fromXDelta, toXDelta, fromYDelta, toYDelta));
        animationSet.setDuration(time);
        return animationSet;
    }
}
