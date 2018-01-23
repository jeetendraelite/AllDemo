package com.AudioVideo;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.alldemo.R;

import java.io.IOException;
import java.net.URI;

public class AudioVideoActivity extends AppCompatActivity {


    ProgressDialog progressDialog;
    VideoView videoView;
    String VideoURL = "http://wolfkeeperuniversity.com/storage/category_video/1513589566_1513589566.mp4";
    String AudioURL = "https://www.android-examples.com/wp-content/uploads/2016/04/Thunder-rumble.mp3";
    MediaPlayer mediaPlayer;
    Button startbtn, stopbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_video);
        videoView=findViewById(R.id.video_view);
        startbtn=findViewById(R.id.btn_start);
        stopbtn=findViewById(R.id.btn_stop);
        progressDialog = new ProgressDialog(AudioVideoActivity.this);
        progressDialog.setTitle("Video Streaming");
        progressDialog.setMessage("BUfferring... ");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        Uri  video = Uri.parse(VideoURL);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(video);

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                    progressDialog.dismiss();
                    videoView.start();
            }
        });
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mediaPlayer.setDataSource(AudioURL);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
            }
        });

    }
}
