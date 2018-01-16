package com.misc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alldemo.R;
import com.koushikdutta.ion.Ion;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IONActivity extends AppCompatActivity {

    private  String Normal_Image_URL="http://www.androidmaster.info/images/dhiraj.jpg";
    private String GIF_IMAGE_URL = "http://www.androidmaster.info/images/gif_image.GIF";

    @BindView(R.id.imageView)
    ImageView img;

    @BindView(R.id.loadImage)
    Button loadsimpleImage;

    @BindView(R.id.loadGifImage)
    Button loadGifImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ion);
        initview();
        ButterKnife.bind(this);
    }

    private void initview() {
        ButterKnife.bind(this);
        loadsimpleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ion.with(img)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .load(Normal_Image_URL);
            }
        });

        loadGifImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ion.with(img)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .load(GIF_IMAGE_URL);
            }
        });


    }
}
