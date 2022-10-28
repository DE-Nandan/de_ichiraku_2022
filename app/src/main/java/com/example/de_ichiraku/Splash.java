package com.example.de_ichiraku;

import androidx.appcompat.app.AppCompatActivity;
import android.view.WindowManager;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT=1250;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Splash.this,
                        MainActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);



    }
}