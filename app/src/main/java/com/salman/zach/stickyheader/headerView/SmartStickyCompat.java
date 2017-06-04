package com.salman.zach.stickyheader.headerView;

import android.view.View;

import com.salman.zach.stickyheader.headerView.listeners.StickyAnimatorListener;


/**
 * Created by salmanZack on 21/4/16.
 */

public class SmartStickyCompat {


    private static StickyAnimatorListener SA_IMPL;

    static {
        SA_IMPL = new SAImpl();
    }

    public static float getTranslationX(View view) {
        return SA_IMPL.getTranslationX(view);
    }

    public static float getTranslationY(View view) {
        return SA_IMPL.getTranslationY(view);
    }

    public static void setTranslationX(View view, float value) {
        SA_IMPL.setTranslationX(view, value);
    }

    public static void setTranslationY(View view, float value) {
        SA_IMPL.setTranslationY(view, value);
    }

    public static void setScaleX(View view, float value) {
        SA_IMPL.setScaleX(view, value);
    }

    public static void setScaleY(View view, float value) {
        SA_IMPL.setScaleY(view, value);
    }

    public static void setAlpha(View view, float value) {
        SA_IMPL.setAlpha(view, value);
    }

    public static float getScaleX(View view) {
        return SA_IMPL.getScaleX(view);
    }

    public static float getScaleY(View view) {
        return SA_IMPL.getScaleY(view);
    }

    public static float getAlpha(View view) {
        return SA_IMPL.getAlpha(view);
    }


    private static class SAImpl implements StickyAnimatorListener {

        @Override
        public float getTranslationX(View view) {
            return view.getTranslationX();
        }

        @Override
        public float getTranslationY(View view) {
            return view.getTranslationY();
        }

        @Override
        public void setTranslationX(View view, float value) {
            view.setTranslationX(value);
        }

        @Override
        public void setTranslationY(View view, float value) {
            view.setTranslationY(value);
        }

        @Override
        public void setScaleX(View view, float value) {
            view.setScaleX(value);
        }

        @Override
        public void setScaleY(View view, float value) {
            view.setScaleY(value);
        }

        @Override
        public float getScaleX(View view) {
            return view.getScaleX();
        }

        @Override
        public float getScaleY(View view) {
            return view.getScaleY();
        }

        @Override
        public void setAlpha(View view, float value) {
            view.setAlpha(value);
        }

        @Override
        public float getAlpha(View view) {
            return view.getAlpha();
        }
    }

}
