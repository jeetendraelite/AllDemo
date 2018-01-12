package com.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.alldemo.R;

public class ActBottomTopAnimation extends AppCompatActivity {


   @BindView(R.id.animImage)
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_bottom_top_animation);
        ButterKnife.bind(this);


        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_file);
        imageView.startAnimation(animation);

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
                    Intent intent = new Intent(ActBottomTopAnimation.this,ActImageAnimation.class);
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
