package com.example.threading;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView1,textView2,textView3;

    private Button startButton,stopButton;

    private volatile boolean stopThreads = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        startButton  = findViewById(R.id.startButton);
        stopButton  = findViewById(R.id.stopButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopThreads = false;
                startColorThread();
                startCounterThread();
                startMoveThread();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopThreads=true;
            }
        });
    }

    private void startMoveThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!stopThreads)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView3.setTranslationX((int)(Math.random()*100));
                        }
                    });
                    try{
                        Thread.sleep(1000);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void startCounterThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 1000 && !stopThreads; i++) {
                    final Integer I = i;
                    textView2.post(new Runnable() {
                        @Override
                        public void run() {
                            textView2.setText(String.valueOf(I));
                        }
                    });
                    try{
                        Thread.sleep(500);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void startColorThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!stopThreads)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView1.setTextColor(Color.rgb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}