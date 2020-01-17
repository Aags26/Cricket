package com.example.cricket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class TossLose extends AppCompatActivity {

    int random;
    private TextView text_View_device_choice;
    Handler setDelay;
    private static Runnable startDelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toss_lose);

        setDelay = new Handler();
        text_View_device_choice = findViewById(R.id.text_View_device_choice);

        random = (int)(Math.random() * 2);

        if(random == 0)
        {
            text_View_device_choice.setText("The device opts to BAT first");
            startDelay = new Runnable() {
                @Override
                public void run() {
                    ball_first();
                }
            };
            setDelay.postDelayed(startDelay, 2500);
        }
        else
        {
            text_View_device_choice.setText("The device opts to BALL first");
            startDelay = new Runnable() {
                @Override
                public void run() {
                    bat_first();
                }
            };
            setDelay.postDelayed(startDelay, 2500);
        }

    }
        public void ball_first()
        {
            Intent intent_balling_first = new Intent(TossLose.this, Balling_first.class);
            startActivity(intent_balling_first);
        }

        public void bat_first()
        {
            Intent intent_batting_first = new Intent(TossLose.this, Batting_first.class);
            startActivity(intent_batting_first);
        }
}
