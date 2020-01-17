package com.example.cricket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText edit_text_name;
    private Button button_sbm;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_text_name = findViewById(R.id.edit_Text_name);

        button_sbm = findViewById(R.id.button_sbm);
        button_sbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edit_text_name.getText().toString();
                if(name.trim().length() == 0)
                {
                    Toast.makeText(MainActivity.this, "Please enter an appropriate name", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent_to_display_rules;

                    intent_to_display_rules = new Intent(MainActivity.this, DisplayRules.class);
                    intent_to_display_rules.putExtra("Name", name);
                    startActivity(intent_to_display_rules);

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to exit the game?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
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

}
