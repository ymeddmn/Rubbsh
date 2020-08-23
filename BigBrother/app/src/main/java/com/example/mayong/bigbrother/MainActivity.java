package com.example.mayong.bigbrother;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.CountDownTimer;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTop, tvBottom;
    private LinearLayout llContainer;
    private BigBrotherView container;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTop = findViewById(R.id.top);
        tvBottom = findViewById(R.id.bottom);
        container = findViewById(R.id.container);
        llContainer = findViewById(R.id.ll_container);
        container.setOnClickListener(this);

    }


    private float dp2px(int dp) {
        return (float) (dp * getResources().getDisplayMetrics().density + 0.5);
    }


    @Override
    public void onClick(View v) {
        final float length = dp2px(50);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                if (index % 2 == 0) {
                    container.setTopY((int) (animatedFraction * length));
                } else {
                    container.setTopY((int) ((1 - animatedFraction) * length));
                }
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                index++;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
