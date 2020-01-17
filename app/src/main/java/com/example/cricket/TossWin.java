package com.example.cricket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TossWin extends AppCompatActivity {

    private Button button_batting, button_balling;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toss_win);

        final Intent intent = getIntent();
        name = intent.getStringExtra("Name");

        button_batting = findViewById(R.id.button_batting);
        button_batting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_batting = new Intent(TossWin.this, Batting_first.class);
                intent_batting.putExtra("Name", name);
                startActivity(intent_batting);
            }
        });

        button_balling = findViewById(R.id.button_balling);
        button_balling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_balling = new Intent(TossWin.this, Balling_first.class);
                intent_balling.putExtra("Name", name);
                startActivity(intent_balling);
            }
        });
    }
}
