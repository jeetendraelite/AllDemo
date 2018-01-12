package com.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.alldemo.R;

public class ActRightLeftAnimation extends AppCompatActivity {

    @BindView(R.id.img1)
    ImageView img;

    @BindView(R.id.txt1)
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_right_left_animation);
        ButterKnife.bind(this);

        TranslateAnimation translateAnimation= new TranslateAnimation(500f,0f,-400f,0f);
        translateAnimation.setDuration(5000);
        translateAnimation.setFillAfter(true);


        img.startAnimation(translateAnimation);
        txt.startAnimation(translateAnimation);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(5000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(ActRightLeftAnimation.this,ActImageAnimation.class);
                    startActivity(intent);
                }

            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
