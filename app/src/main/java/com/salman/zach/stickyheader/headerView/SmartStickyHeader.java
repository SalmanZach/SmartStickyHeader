package com.salman.zach.stickyheader.headerView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.salman.zach.stickyheader.headerView.animator.SmartHeaderAnimator;
import com.salman.zach.stickyheader.headerView.listeners.StickyHeaderTouchListener;

/**
 * Created by salmanZack on 21/4/16.
 */

public abstract class SmartStickyHeader {

    protected Context mContext;

    protected View mHeader;
    protected int mMinHeightHeader;
    protected int mHeightHeader;
    private SmartHeaderAnimator mSmartHeaderAnimator;

    protected SmartStickyHeader(Context context, View header, int minHeightHeader, SmartHeaderAnimator smartHeaderAnimator) {
        mContext = context;
        mHeader = header;
        mMinHeightHeader = minHeightHeader;
        mSmartHeaderAnimator = smartHeaderAnimator;
    }

    protected abstract View getScrollingView();

    /**
     * Init method called when the SmartStickyHeader is created
     */
    protected void init() {
        setupAnimator();
        measureHeaderHeight();
    }

    protected void build(StickyHeaderTouchListener headerTouchListener) {
        setOnTouchListenerOnHeader(headerTouchListener);
        init();
    }

    private void measureHeaderHeight() {
        int height = mHeader.getHeight();

        if (height == 0) {
            ViewGroup.LayoutParams lp = mHeader.getLayoutParams();
            if (lp != null) {
                height = lp.height;
            }
        }

        if (height > 0) {
            setHeightHeader(height);
        }

        mHeader.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mHeightHeader != mHeader.getHeight()) {
                    setHeightHeader(mHeader.getHeight());
                }
            }
        });
    }

    protected void setHeightHeader(final int heightHeader) {
        mHeightHeader = heightHeader;
        mSmartHeaderAnimator.setHeightHeader(heightHeader, mMinHeightHeader);
    }

    public void onScroll(int yScroll) {
        mSmartHeaderAnimator.onScroll(yScroll);
    }

    private void setupAnimator() {
        mSmartHeaderAnimator.setupAnimator(mHeader);
    }

    public void setMinHeightHeader(int minHeightHeader) {
        this.mMinHeightHeader = minHeightHeader;
        mSmartHeaderAnimator.setHeightHeader(mHeightHeader, mMinHeightHeader);
    }

    public void setOnTouchListenerOnHeader(StickyHeaderTouchListener headerTouchListener) {
        SmartHeaderUtility.setOnTouchListenerOnHeader(mHeader, getScrollingView(), headerTouchListener);

    }

    public View getHeader() {
        return mHeader;
    }

}
