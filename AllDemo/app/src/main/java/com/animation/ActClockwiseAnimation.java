package com.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.alldemo.R;


// Code for ClockWise Animation.

public class ActClockwiseAnimation extends AppCompatActivity {

    @BindView(R.id.img2)
    ImageView imageView;

    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_clockwise_animation);
        ButterKnife.bind(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(ActClockwiseAnimation.this,R.anim.anim_clockwise);
                imageView.startAnimation(animation);

            }
        });

    }
}
