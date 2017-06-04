package com.salman.zach.stickyheader.headerView.listeners;

import android.view.View;

/**
 * Created by Zach on 6/2/2017.
 */

public interface StickyAnimatorListener {
    float getTranslationX(View view);

    float getTranslationY(View view);

    void setTranslationX(View view, float value);

    void setTranslationY(View view, float value);

    void setScaleX(View view, float value);

    void setScaleY(View view, float value);

    float getScaleX(View view);

    float getScaleY(View view);

    void setAlpha(View view, float value);

    float getAlpha(View view);
}
