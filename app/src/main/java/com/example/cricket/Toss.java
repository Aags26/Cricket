package com.example.cricket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Toss extends AppCompatActivity {
    private RadioGroup radioGroup;
    private static RadioButton radio_choose;
    private Button button_toss;
    private ImageView imageView_toss;
    int random_number;
    private static String choice;
    Handler setDelay;
    Runnable startDelay;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toss);

        Intent intent = getIntent();
        name = intent.getStringExtra("Name");

        setDelay = new Handler();

        imageView_toss = findViewById(R.id.image_View_coin);
        imageView_toss.setImageResource(R.drawable.heads);

        radioGroup = findViewById(R.id.radio_Group_toss);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selected_id = radioGroup.getCheckedRadioButtonId();
                radio_choose = findViewById(selected_id);
                choice = radio_choose.getText().toString();
            }
        });

        button_toss = findViewById(R.id.button_toss);
        button_toss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (radioGroup.getCheckedRadioButtonId() == -1)
                {
                    Toast.makeText(getApplicationContext(), "Please Choose First", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    random_number = (int) (Math.random() * 2);

                    if (random_number == 0)
                    {
                        imageView_toss.setImageResource(R.drawable.heads);
                        Toast.makeText(Toss.this, "Heads", Toast.LENGTH_SHORT).show();
                    }

                    else
                    {
                        imageView_toss.setImageResource(R.drawable.tails);
                        Toast.makeText(Toss.this, "Tails", Toast.LENGTH_SHORT).show();
                    }


                    RotateAnimation rotate_coin = new RotateAnimation(0.5f, 360,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF,
                            0.5f);
                    rotate_coin.setDuration(1000);
                    imageView_toss.startAnimation(rotate_coin);
                    button_toss.setVisibility(View.INVISIBLE);

                    startDelay = new Runnable() {
                        @Override
                        public void run() {
                            if(choice.equals("Heads(H)"))
                            {

                                if(random_number == 0)
                                {
                                    win();
                                }
                                else
                                {
                                    lose();
                                }
                            }
                            else
                            {
                                if(random_number == 1)
                                {
                                    win();
                                }
                                else
                                {
                                    lose();
                                }
                            }
                        }
                    };
                    setDelay.postDelayed(startDelay, 2000);

                }
            }

        });
    }
    public void win()
    {
        Intent intent = new Intent(Toss.this, TossWin.class);
        intent.putExtra("Name", name);
        startActivity(intent);
    }
    public void lose()
    {
        Intent intent = new Intent(Toss.this, TossLose.class);
        intent.putExtra("Name", name);
        startActivity(intent);
    }
}
