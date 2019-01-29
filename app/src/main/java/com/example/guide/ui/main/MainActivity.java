package com.example.guide.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guide.R;
import com.example.guide.ui.home.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTimer = new Timer();

        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                //lancement HOmeactivity
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); //ou dans le manifest, dans l'activité  -> android:noHistory="true"
            }
        }, 2000);
    }


}
