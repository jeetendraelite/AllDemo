package com.misc;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.alldemo.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgressActivity extends AppCompatActivity {


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    Handler  handler;
    Runnable runnable;
    Timer timer;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);


        progressBar.setProgress(0);
        progressBar.setMax(50);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if(i<50){
                    progressBar.setProgress(i);
                }else{
                    progressBar.setVisibility(View.GONE);
                    timer.cancel();
                }

            }
        };

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                i=i+1;
                handler.post(runnable);
            }
        },4000,200);


    }
}
