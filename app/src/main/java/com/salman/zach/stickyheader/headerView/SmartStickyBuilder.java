package com.salman.zach.stickyheader.headerView;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.salman.zach.stickyheader.headerView.animator.SmartHeaderAnimator;
import com.salman.zach.stickyheader.headerView.animator.StickyAnimatorDefault;
import com.salman.zach.stickyheader.headerView.listeners.StickyHeaderTouchListener;
import com.salman.zach.stickyheader.headerView.supportedView.StickWithRecyclerView;
import com.salman.zach.stickyheader.headerView.supportedView.StickWithScrollView;


/**
 * Created by salmanZack on 21/4/16.
 */

public abstract class SmartStickyBuilder {

    protected final Context mContext;
    protected StickyHeaderTouchListener headerTouchListener;
    protected View mHeader;
    protected int mMinHeight;
    protected SmartHeaderAnimator mAnimator;

    protected SmartStickyBuilder(final Context context) {
        mContext = context;
        mMinHeight = 0;
        headerTouchListener = null;
    }

    public static RecyclerViewBuilder stickTo(final ViewGroup recyclerView) {
        SmartHeaderUtility.checkRecyclerView(recyclerView);
        return new RecyclerViewBuilder(recyclerView);
    }

    public static ScrollViewBuilder stickTo(final NestedScrollView scrollView) {
        return new ScrollViewBuilder(scrollView);
    }

    public abstract SmartStickyHeader build();

    public SmartStickyBuilder setHeader(final View header) {
        mHeader = header;
        return this;
    }

    public SmartStickyBuilder setHeaderTouchListener(final StickyHeaderTouchListener headerTouchListener) {
        this.headerTouchListener = headerTouchListener;
        return this;
    }


    public SmartStickyBuilder minHeightHeader(final int minHeight) {
        mMinHeight = minHeight;
        return this;
    }

    public SmartStickyBuilder animator(final SmartHeaderAnimator animator) {
        mAnimator = animator;
        return this;
    }


    /*
    * Header Bing for RecyclerView
    * */
    public static class RecyclerViewBuilder extends SmartStickyBuilder {

        private final RecyclerView mRecyclerView;

        protected RecyclerViewBuilder(final ViewGroup mRecyclerView) {
            super(mRecyclerView.getContext());
            this.mRecyclerView = (RecyclerView) mRecyclerView;
        }

        @Override
        public StickWithRecyclerView build() {

            //if the animator has not been set, the default one is used
            if (mAnimator == null) {
                mAnimator = new StickyAnimatorDefault();
            }

            final StickWithRecyclerView stickWithRecyclerView = new StickWithRecyclerView(mContext, mRecyclerView, mHeader, mMinHeight, mAnimator);
            stickWithRecyclerView.build(headerTouchListener);

            return stickWithRecyclerView;
        }

    }

    /*
    * Header Bing for ScrollView
    * */
    public static class ScrollViewBuilder extends SmartStickyBuilder {

        private final NestedScrollView mScrollView;

        protected ScrollViewBuilder(final NestedScrollView scrollView) {
            super(scrollView.getContext());
            this.mScrollView = scrollView;
        }

        @Override
        public StickWithScrollView build() {

            //if the animator has not been set, the default one is used
            if (mAnimator == null) {
                mAnimator = new StickyAnimatorDefault();
            }

            final StickWithScrollView stickWithScrollView = new StickWithScrollView(mContext, mScrollView, mHeader, mMinHeight, mAnimator);

            stickWithScrollView.build(headerTouchListener);

            return stickWithScrollView;
        }

    }


}
