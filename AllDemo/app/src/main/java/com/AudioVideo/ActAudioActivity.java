package com.AudioVideo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alldemo.R;

public class ActAudioActivity extends AppCompatActivity implements View.OnClickListener {


    Button startbtn,stopbtn,pausebtn;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_audio);
        startbtn=findViewById(R.id.btn_play);
        stopbtn=findViewById(R.id.btn_stop);
        pausebtn=findViewById(R.id.btn_pause);
        mediaPlayer= MediaPlayer.create(this,R.raw.sampleaudio);
        startbtn.setOnClickListener(this);
        stopbtn.setOnClickListener(this);
        pausebtn.setOnClickListener(this);

    }


    public void PlaySound(){
        mediaPlayer.start();
    }
    public void PauseSound(){
        mediaPlayer.pause();
    }
    public void StopSound(){
        mediaPlayer.stop();
      //  mediaPlayer= MediaPlayer.create(this,R.raw.sampleaudio);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play:
                PlaySound();
                break;

            case R.id.btn_stop:
                StopSound();
                break;
            case R.id.btn_pause:
                PauseSound();
                break;
        }
    }
}
