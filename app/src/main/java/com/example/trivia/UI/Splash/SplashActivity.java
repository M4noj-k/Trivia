package com.example.trivia.UI.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.trivia.Data.Room.PreferenceHelper;
import com.example.trivia.R;
import com.example.trivia.UI.Dashboard.DashboardActivity;
import com.example.trivia.Util.Constants;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //Move the page where user was in last:
                Intent intent=new Intent(SplashActivity.this, DashboardActivity.class);
                startActivity(intent);
                // close this activity
                finish();

            }
        }, SPLASH_TIME_OUT);
    }
}
