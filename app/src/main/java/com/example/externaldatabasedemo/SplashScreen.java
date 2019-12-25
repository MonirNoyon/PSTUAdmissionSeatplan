package com.example.externaldatabasedemo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        EasySplashScreen splashScreen = new EasySplashScreen(SplashScreen.this);
        splashScreen.withFullScreen();
        splashScreen.withTargetActivity(MainActivity.class);
        splashScreen.withSplashTimeOut(2000);
        splashScreen.withBackgroundResource(R.drawable.splash);
        View view = splashScreen.create();
        setContentView(view);
    }
}
