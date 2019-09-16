package com.ohtel.ohtel.screens.screens.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.ohtel.ohtel.R;
import com.ohtel.ohtel.screens.screens.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    Handler handler;
    private final static int time_delay = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
    }
    private void init() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, time_delay);
    }
}
