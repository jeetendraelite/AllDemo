package com.ChooserShareBUtton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alldemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooserShareActivity extends AppCompatActivity {

    @BindView(R.id.btn_share)
    Button btn;

    Intent shareIntent;
    String shareBody="This is my Sharing Body Text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser_share);

        ButterKnife.bind(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareIntent= new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"MY APP");
                shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(shareIntent,"Share Via .."));
            }
        });

    }

}
