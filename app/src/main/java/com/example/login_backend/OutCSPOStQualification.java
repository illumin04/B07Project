package com.example.login_backend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class OutCSPOStQualification extends AppCompatActivity {
    private boolean q1_flag = false;
    private boolean q2_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_cspost_qualification);
        RadioButton q1_true = findViewById(R.id.q1_true);
        RadioButton q1_false = findViewById(R.id.q1_false);
        RadioButton q2_true = findViewById(R.id.q2_true);
        RadioButton q2_false = findViewById(R.id.q2_false);

        TextView qualify = findViewById(R.id.qualify_tv);
        qualify.setVisibility(View.INVISIBLE);
        TextView not_qualify = findViewById(R.id.not_qualify_tv);
        not_qualify.setVisibility(View.INVISIBLE);

        Button checkButton = findViewById(R.id.checkButton);
        Button backButton = findViewById(R.id.backButton);
        Button homeButton = findViewById(R.id.homeButton);

        q1_true.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    q1_flag = true;
                }
            }
        });

        q1_false.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    q1_flag = false;
                }
            }
        });

        q2_true.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    q2_flag = true;
                }
            }
        });

        q2_false.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    q2_flag = false;
                }
            }
        });

        // Handle the back button click
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the current activity and go back
                finish();
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q1_flag && q2_flag) {
                    qualify.setVisibility(View.VISIBLE);
                    not_qualify.setVisibility(View.INVISIBLE);
                }
                else {
                    not_qualify.setVisibility(View.VISIBLE);
                    qualify.setVisibility(View.INVISIBLE);
                }

            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OutCSPOStQualification.this, StudentHomePage.class);
                startActivity(intent);
            }
        });
    }
}