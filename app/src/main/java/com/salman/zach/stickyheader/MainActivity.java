package com.salman.zach.stickyheader;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.salman.zach.stickyheader.headerView.SmartStickyBuilder;
import com.salman.zach.stickyheader.headerView.animator.AnimatorBuilder;
import com.salman.zach.stickyheader.headerView.animator.SmartBaseHeaderAnimator;
import com.salman.zach.stickyheader.headerView.animator.StickyAnimatorDefault;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView headerImage;
    private RelativeLayout headerLayout;
    private NestedScrollView scroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        headerImage = (ImageView) findViewById(R.id.header_image);
        headerLayout = (RelativeLayout) findViewById(R.id.header_layout);
        scroller = (NestedScrollView) findViewById(R.id.scroller);

        final SmartBaseHeaderAnimator animator = new StickyAnimatorDefault() {

            @Override
            public AnimatorBuilder getAnimatorBuilder() {
                final View imageOne, imageTwo, imageThree, imageFour, imageFive, logo, foreground;
                imageOne = getHeader().findViewById(R.id.image_one);
                imageTwo = getHeader().findViewById(R.id.image_two);
                imageThree = getHeader().findViewById(R.id.image_three);
                imageFour = getHeader().findViewById(R.id.image_four);
                imageFive = getHeader().findViewById(R.id.image_five);
                logo = getHeader().findViewById(R.id.logo);
                foreground = getHeader().findViewById(R.id.foreground);

                int space = (headerImage.getWidth()) / 5;


                return AnimatorBuilder.create()
                        .applyTranslation(imageOne, new Point((space - imageOne.getWidth()) / 2, 0))
                        .applyTranslation(imageTwo, new Point(space + ((space - imageOne.getWidth()) / 2), 0))
                        .applyTranslation(imageThree, new Point((2 * space) + ((space - imageOne.getWidth()) / 2), 0))
                        .applyTranslation(imageFour, new Point((3 * space) + ((space - imageOne.getWidth()) / 2), 0))
                        .applyTranslation(imageFive, new Point((4 * space) + ((space - imageOne.getWidth()) / 2), 0))
                        .applyTranslation(logo, new Point(-headerLayout.getWidth() / 2, 0))
                        .applyFade(logo, 0)
                        .applyFade(foreground, 1);
            }
        };
        SmartStickyBuilder
                .stickTo(scroller)
                .setHeader(headerLayout)
                .minHeightHeader(getResources().getDimensionPixelSize(R.dimen.header_height))
                .animator(animator)
                .build();

        findViewById(R.id.image_one).setOnClickListener(this);
        findViewById(R.id.image_two).setOnClickListener(this);
        findViewById(R.id.image_three).setOnClickListener(this);
        findViewById(R.id.image_four).setOnClickListener(this);
        findViewById(R.id.image_five).setOnClickListener(this);
        if (true) {

        } else {

        }


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.image_one:
                animateClick(findViewById(R.id.image_one));

                break;
            case R.id.image_two:
                animateClick(findViewById(R.id.image_two));

                break;
            case R.id.image_three:
                animateClick(findViewById(R.id.image_three));

                break;
            case R.id.image_four:
                animateClick(findViewById(R.id.image_four));

                break;
            case R.id.image_five:
                animateClick(findViewById(R.id.image_five));
                break;
        }

    }

    private void animateClick(View view) {
        Animation scale = new ScaleAnimation(1f, 1.3f, 1f, 1.3f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setRepeatMode(Animation.REVERSE);
        scale.setRepeatCount(1);
        scale.setDuration(200);
        scale.setInterpolator(new OvershootInterpolator());
        view.startAnimation(scale);
    }
}
