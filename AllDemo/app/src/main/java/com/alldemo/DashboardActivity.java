package com.alldemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.AudioVideo.ActAudioActivity;
import com.AudioVideo.ActVideoPlayer;
import com.ChooserShareBUtton.ChooserShareActivity;
import com.DateTimePicker.DateTimeActivity;
import com.animation.ActColorAnimation;
import com.animation.ActImageAnimation;
import com.animation.ActBottomTopAnimation;
import com.animation.ActRightLeftAnimation;
import com.animation.ActClockwiseAnimation;
import com.bottomsheet.BottomSheetActivity;
import com.misc.ActForResultDemo;
import com.misc.IONActivity;
import com.misc.NavigationBottomActivity;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


      // startActivity(new Intent(this, ActVideoPlayer.class));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.imageAnimation) {
            // Handle the camera action

            Intent intent = new Intent(this, ActImageAnimation.class);
            //Intent intent = new Intent(this, ActImageAnimation.class);
            startActivity(intent);


        } else if (id == R.id.colorAnimation) {
            Intent intent = new Intent(this, ActColorAnimation.class);
            startActivity(intent);

        } else if (id == R.id.bottomTopAnimation) {
            Intent intent = new Intent(this, ActBottomTopAnimation.class);
            startActivity(intent);
        } else if (id == R.id.rightLeftAnimation) {
            Intent intent = new Intent(this, ActRightLeftAnimation.class);
            startActivity(intent);
        } else if (id == R.id.clockwiseAnimation) {
            Intent intent = new Intent(this, ActClockwiseAnimation.class);
            startActivity(intent);
        } else if (id == R.id.ion_eg) {
            Intent intent = new Intent(this, IONActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.Bottomsheet) {
            Intent intent = new Intent(this, BottomSheetActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.navigation_Bottom) {
            Intent intent = new Intent(this, NavigationBottomActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.chooser_share) {
            Intent intent = new Intent(this, ChooserShareActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.date_timepicker) {
            Intent intent = new Intent(this, DateTimeActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.video_stream) {
            Intent intent = new Intent(this, ActVideoPlayer.class);
            startActivity(intent);
        }
        else if (id == R.id.audio_stream) {
            Intent intent = new Intent(this, ActAudioActivity.class);
            startActivity(intent);
        }else if (id == R.id.Activity_For_Result) {
            Intent intent = new Intent(this, ActForResultDemo.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
