package com.salman.zach.stickyheader.headerView;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.salman.zach.stickyheader.headerView.listeners.StickyHeaderTouchListener;

import java.lang.ref.WeakReference;

/**
 * Created by salmanZack on 21/4/16.
 */

public class SmartHeaderUtility {

    private static final String RECYCLERVIEW_CLASS_NAME = "android.support.v7.widget.RecyclerView";

    public static void executeOnGlobalLayout(View view, final Runnable runnable) {
        final WeakReference<View> viewReference = new WeakReference<>(view);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                removeOnGlobalLayoutListener(viewReference.get(), this);
                runnable.run();
            }
        });

    }

    public static void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener victim) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            //noinspection deprecation
            view.getViewTreeObserver().removeGlobalOnLayoutListener(victim);
        } else {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(victim);
        }
    }

    private static boolean isAClick(float startX, float endX, float startY, float endY) {
        float differenceX = Math.abs(startX - endX);
        float differenceY = Math.abs(startY - endY);
        if (differenceX > 10/* =5 */ || differenceY > 10) {
            return false;
        }
        return true;
    }

    public static void setOnTouchListenerOnHeader(@NonNull View header, @Nullable View scrollingView, @Nullable final StickyHeaderTouchListener stickyHeaderTouchListener) {


        final WeakReference<View> scrollingViewReference = new WeakReference<>(scrollingView);
        final WeakReference<View> headerReference = new WeakReference<>(header);

        header.setOnTouchListener(new View.OnTouchListener() {

            boolean mDownEventDispatched = false;
            private float startX;
            private float startY;

            @Override
            public boolean onTouch(final View v, final MotionEvent event) {

                boolean eventConsumed = false;
                final View scrollingView = scrollingViewReference.get();

                if (scrollingView != null) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:
                            startX = event.getX();
                            startY = event.getY();
                            return true;

                        case MotionEvent.ACTION_MOVE:

                            final View header = headerReference.get();

                            if (!mDownEventDispatched) {
                                // if moving, create a fake down event for the scrollingView to start the scroll. the y of the touch in the scrolling view is the y coordinate of the touch in the header + the translation of the header
                                final MotionEvent downEvent = MotionEvent.obtain(event.getDownTime() - 1, event.getEventTime() - 1, MotionEvent.ACTION_DOWN, event.getX(), event.getY() + SmartStickyCompat.getTranslationY(header), 0);
                                scrollingView.dispatchTouchEvent(downEvent);
                                mDownEventDispatched = true;
                            }

                            //dispatching the move event. we need to create a fake motionEvent using a different y coordinate related to the scrolling view
                            final MotionEvent moveEvent = MotionEvent.obtain(event.getDownTime(), event.getEventTime(), MotionEvent.ACTION_MOVE, event.getX(), event.getY() + SmartStickyCompat.getTranslationY(header), 0);
                            scrollingView.dispatchTouchEvent(moveEvent);

                            break;
                        case MotionEvent.ACTION_UP:
                            // when action up, dispatch an action cancel to avoid a possible click

                            float endX = event.getX();
                            float endY = event.getY();
                            if (isAClick(startX, endX, startY, endY)) {
                                Log.e("PG", "Clicked Banner: ");
                                // WE HAVE A CLICK!!
                                if (stickyHeaderTouchListener != null)
                                    stickyHeaderTouchListener.onHeaderClicked();

                                return true;

                            }
                            Log.e("PG", "Scrolled Banner: ");
                            // WE HAVE A SCROLL!!
                            if (stickyHeaderTouchListener != null)
                                stickyHeaderTouchListener.onHeaderScrolled();


                            final MotionEvent cancelEvent = MotionEvent.obtain(event.getDownTime(), event.getEventTime(), MotionEvent.ACTION_CANCEL, event.getX(), event.getY(), 0);
                            scrollingView.dispatchTouchEvent(cancelEvent);
                            mDownEventDispatched = false;
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            scrollingView.dispatchTouchEvent(event);
                            mDownEventDispatched = false;
                            break;
                    }

                    eventConsumed = true;
                }

                return eventConsumed;
            }
        });

    }


    static boolean hasRecyclerView() {
        try {
            Class.forName(RECYCLERVIEW_CLASS_NAME);
            return true;
        } catch (ClassNotFoundException ignored) {
            return false;
        }
    }

    public static void checkRecyclerView(ViewGroup recyclerView) {
        checkRecyclerViewOnClassPath();

        Class clazz = recyclerView.getClass();
        while (clazz != null) {
            if (clazz.getName().equals(RECYCLERVIEW_CLASS_NAME)) {
                return;
            }
            clazz = clazz.getSuperclass();
        }

        throw new IllegalArgumentException("ViewGroup " + recyclerView.getClass().getName() + " not supported");

    }

    public static void checkRecyclerViewOnClassPath() {
        if (!hasRecyclerView()) {
            throw new NoClassDefFoundError("RecyclerView not found" +
                    "make sure that you have it in your dependencies");
        }
    }

}
