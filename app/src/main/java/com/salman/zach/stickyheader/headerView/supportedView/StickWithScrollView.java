package com.salman.zach.stickyheader.headerView.supportedView;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.salman.zach.stickyheader.headerView.SmartHeaderUtility;
import com.salman.zach.stickyheader.headerView.SmartStickyHeader;
import com.salman.zach.stickyheader.headerView.animator.SmartHeaderAnimator;


/**
 * Created by salmanZack on 21/4/16.
 */

public class StickWithScrollView extends SmartStickyHeader {

    private NestedScrollView mScrollView;

    public StickWithScrollView(final Context context, final NestedScrollView scrollView, final View header, final int minHeightHeader, final SmartHeaderAnimator smartHeaderAnimator) {
        super(context, header, minHeightHeader, smartHeaderAnimator);
        this.mScrollView = scrollView;
    }

    @Override
    protected View getScrollingView() {
        return mScrollView;
    }

    protected void init() {
        super.init();
        setupOnScrollListener();
        mScrollView.setClipToPadding(false);
    }

    @Override
    protected void setHeightHeader(int heightHeader) {
        super.setHeightHeader(heightHeader);

        // create a placeholder adding a padding to the scrollview behind the header
        mScrollView.setPadding(
                mScrollView.getPaddingLeft(),
                mScrollView.getPaddingTop() + heightHeader,
                mScrollView.getPaddingRight(),
                mScrollView.getPaddingBottom()
        );
    }

    private void setupOnScrollListener() {

        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                onScroll(-mScrollView.getScrollY());
            }
        });

        //init scroll listener when the view is ready
        SmartHeaderUtility.executeOnGlobalLayout(mScrollView, new Runnable() {
            @Override
            public void run() {
                onScroll(-mScrollView.getScrollY());
            }
        });
    }


}
