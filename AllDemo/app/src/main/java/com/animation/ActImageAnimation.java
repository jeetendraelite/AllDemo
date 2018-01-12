package com.animation;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.alldemo.R;


public class ActImageAnimation extends AppCompatActivity {

   @BindView(R.id.layout1)
   LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_image_animation);
        ButterKnife.bind(this);
        linearLayout.setBackgroundResource(R.drawable.animation);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        AnimationDrawable animationDrawable= (AnimationDrawable) linearLayout.getBackground();
        if(hasFocus){
            animationDrawable.start();
        }else{
            animationDrawable.stop();
        }

    }
}
