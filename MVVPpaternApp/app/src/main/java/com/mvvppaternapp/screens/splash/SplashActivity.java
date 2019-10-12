package com.mvvppaternapp.screens.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.mvvppaternapp.R;
import com.mvvppaternapp.screens.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_splash);
        init();

    }//End onCreate

    private void init(){
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
            }
        }, 4000);
    }
}
