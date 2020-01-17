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

public class Balling_second extends AppCompatActivity implements View.OnClickListener {

    private TextView text_View_device_target, text_View_your_number, text_View_device_number, text_View_total;
    private Button button_numbers[] = new Button[10];
    int i, random_device_number, device_batting_score = 0, your_batting_score;
    boolean b = true;
    Handler setDelay;
    Runnable startDelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balling_second);

        setDelay = new Handler();

        Intent intent = getIntent();
        String device_target = intent.getStringExtra("Device target");
        your_batting_score = intent.getIntExtra("Your Total Batting score", 0);

        text_View_your_number = findViewById(R.id.text_View_your_number);
        text_View_device_number = findViewById(R.id.text_View_device_number);
        text_View_total = findViewById(R.id.text_view_total);

        text_View_device_target = findViewById(R.id.text_View_your_target);
        text_View_device_target.setText(device_target);

        for (i = 0; i < 10; i++) {
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
                        Balling_second.super.onBackPressed();
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
    public void onClick(View v)
    {
        random_device_number = (int) ((Math.random() * 10) + 1);

        text_View_your_number.setText(((Button) v).getText().toString());
        text_View_device_number.setText(Integer.toString(random_device_number));



        if (random_device_number != Integer.parseInt(((Button) v).getText().toString()) && b)
       {

           device_batting_score = device_batting_score + random_device_number;
           text_View_total.setText(Integer.toString(device_batting_score));
           if(device_batting_score > your_batting_score)
           {
               checkStatus();
           }
       }
        else
            checkStatus();
       }

       public void checkStatus()
       {
           for (i = 0; i < 10; i++)
           {
               button_numbers[i].setVisibility(View.INVISIBLE);
           }
           startDelay = new Runnable() {
               @Override
               public void run() {
                   if (device_batting_score < your_batting_score)
                   {
                       Intent intent_win = new Intent(Balling_second.this, Result.class);
                       String match_win = "You won the match";
                       intent_win.putExtra("result", match_win);
                       startActivity(intent_win);
                   } else if(device_batting_score > your_batting_score)
                   {
                       Intent intent_lose = new Intent(Balling_second.this, Result.class);
                       String match_lose = "You lose the match";
                       intent_lose.putExtra("result", match_lose);
                       startActivity(intent_lose);
                   }
                   else
                   {
                       Intent intent_tie = new Intent(Balling_second.this, Toss.class);
                       startActivity(intent_tie);
                   }

               }
           };
           setDelay.postDelayed(startDelay, 1500);
       }


    }







