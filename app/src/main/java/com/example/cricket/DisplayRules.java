package com.example.cricket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayRules extends AppCompatActivity{
    TextView text_View_hello, text_View_name;
    CheckBox checkBox_sbm;
    Button button_next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_rules);
        text_View_hello = findViewById(R.id.text_View_hello);
        text_View_name = findViewById(R.id.text_View_display_name);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("Name");
        text_View_name.setText(name + ".");

        checkBox_sbm = findViewById(R.id.checkBox_Submit);
        button_next = findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox_sbm.isChecked())
                {
                    Intent intent = new Intent(DisplayRules.this, Toss.class);
                    intent.putExtra("Name", name);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(DisplayRules.this, "Please check the box before you proceed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
