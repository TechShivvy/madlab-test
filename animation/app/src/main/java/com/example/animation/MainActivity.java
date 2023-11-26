package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout car;
    Button reverse,forward,card,flip;
    private int start = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        car=findViewById(R.id.carLayout);
        forward=findViewById(R.id.forwardButton);
        reverse=findViewById(R.id.reverseButton);
        flip=findViewById(R.id.flipButton);
        card=findViewById(R.id.card);

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        car.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ObjectAnimator animator;
                                animator = ObjectAnimator.ofFloat(car,"translationY",start,start+1000);
                                animator.setDuration(1000);
                                animator.start();
                                start+=250;
                            }
                        },0);
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        car.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                car.animate().scaleX(2f);
                                car.animate().scaleY(2f);
                            }
                        },100);
                    }
                }).start();
//                car.animate().scaleX(1.5f);
//                car.animate().scaleY(1.5f);

//                animator = ObjectAnimator.ofFloat(car,"translationX",start,start+50);
//                animator.setDuration(1000);
//                animator.start();

            }
        });

        reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                car.setScaleX(1f);
//                car.setScaleY(1f);
//                ObjectAnimator animator = ObjectAnimator.ofFloat(car,"translationX",start,start-50);
//                animator.setDuration(1000);
//                animator.start();
//                start-=50;
// Scale Animation
                ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(car, "translationX", start, start - 300);

                ObjectAnimator scaleAnimatorX = ObjectAnimator.ofFloat(car, "scaleX", 2.0f);
                ObjectAnimator scaleAnimatorY = ObjectAnimator.ofFloat(car, "scaleY", 2.0f);

                // Translation Animation

                // Set up AnimatorSet to play both animations together
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(translationAnimator,scaleAnimatorX, scaleAnimatorY);
                animatorSet.setDuration(1000); // Set the duration of the animations

                // Start the AnimatorSet
                animatorSet.start();

                // Update the starting position for the next click
                start -=100;


//                car.animate().translationX(-10f);
            }
        });

        flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(card,"rotationY",0,180);
                animator.setDuration(1000);
                animator.start();
            }
        });

    }
}