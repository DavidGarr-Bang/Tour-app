package com.example.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        prefs = getSharedPreferences("com.example.test", MODE_PRIVATE);

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs


            Intent i = new Intent(SplashScreen.this,IntroAtivity.class);
            startActivity(i);
            prefs.edit().putBoolean("firstrun", false).commit();
        }else {

            Thread myThread = new Thread(){
                @Override
                public void run() {
                    try{
                        //3000 milliseconds -> 3 seconds
                        sleep(2000);
                        Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                        startActivity(intent);
                        //destroy
                        finish();

                    } catch (InterruptedException e){
                        e.printStackTrace();

                    }
                }
            };
            myThread.start(); //call run() method

        }
    }
}
