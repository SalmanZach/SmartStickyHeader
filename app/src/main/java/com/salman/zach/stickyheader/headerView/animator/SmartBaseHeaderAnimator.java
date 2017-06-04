package com.salman.zach.stickyheader.headerView.animator;

import com.salman.zach.stickyheader.headerView.SmartStickyCompat;

/**
 * Created by salmanZach on 21/4/16.
 */

public class SmartBaseHeaderAnimator extends SmartHeaderAnimator {

    private float mTranslationRatio;

    @Override
    protected void onAnimatorAttached() {
        //nothing to do
    }

    @Override
    protected void onAnimatorReady() {
        //nothing to do
    }

    @Override
    public void onScroll(int scrolledY) {
        SmartStickyCompat.setTranslationY(mHeader, Math.max(scrolledY, getMaxTranslation()));
        mTranslationRatio = calculateTranslationRatio(scrolledY);
    }

    public float getTranslationRatio() {
        return mTranslationRatio;
    }

    private float calculateTranslationRatio(int scrolledY) {
        //TODO  divisor should't be != 0
        return (float) scrolledY / (float) getMaxTranslation();
    }

}
