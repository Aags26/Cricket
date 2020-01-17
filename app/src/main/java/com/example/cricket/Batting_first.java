package com.example.cricket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Batting_first extends AppCompatActivity implements View.OnClickListener {

    private TextView text_View_your_number, text_View_device_number, text_View_total;
    private Button button_numbers[] = new Button[10];
    int i, your_total_score = 0, random_device_number;
    Handler setDelay;
    Runnable startDelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batting_first);

        setDelay = new Handler();


        text_View_your_number = findViewById(R.id.text_View_your_number);
        text_View_device_number = findViewById(R.id.text_View_device_number);
        text_View_total = findViewById(R.id.text_view_total);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");

        for(i = 0; i < 10; i++)
        {
            String buttonId = "button_" + i;
            int resID = getResources().getIdentifier(buttonId, "id", getPackageName());
            button_numbers[i] = findViewById(resID);
            button_numbers[i].setOnClickListener(this);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to leave?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Batting_first.super.onBackPressed();
            }
        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public void onClick(View v) {

        text_View_your_number.setText(((Button) v).getText().toString());

        random_device_number = (int)((Math.random() * 10) + 1);
        text_View_device_number.setText(Integer.toString(random_device_number));

        if(random_device_number != Integer.parseInt(((Button) v).getText().toString()))
        {
            your_total_score = your_total_score + (Integer.parseInt(((Button) v).getText().toString()));
            text_View_total.setText(Integer.toString(your_total_score));
        }
        else
        {
            for(i = 0; i < 10; i++)
            {
                button_numbers[i].setVisibility(View.INVISIBLE);
            }
            Toast.makeText(Batting_first.this, "YOU ARE OUT", Toast.LENGTH_SHORT).show();
            startDelay = new Runnable() {
                @Override
                public void run() {
                    String device_target = "Device needs " + (your_total_score + 1) + " runs to win.";

                    Intent intent_your_balling = new Intent(Batting_first.this, Balling_second.class);
                    intent_your_balling.putExtra("Your Total Batting score", your_total_score);
                    intent_your_balling.putExtra("Device target", device_target);
                    startActivity(intent_your_balling);
                }
            };
            setDelay.postDelayed(startDelay, 2500);
        }
    }
}
