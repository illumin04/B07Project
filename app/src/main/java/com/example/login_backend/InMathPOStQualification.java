package com.example.login_backend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class InMathPOStQualification extends AppCompatActivity {
    private boolean q1_flag = false;
    private boolean q2_flag = false;
    private boolean q3_flag = false;
    private boolean q4_flag = false;
    private boolean q5_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_math_post_qualification);
        RadioButton q1_true = findViewById(R.id.q1_true);
        RadioButton q1_false = findViewById(R.id.q1_false);
        RadioButton q2_true = findViewById(R.id.q2_true);
        RadioButton q2_false = findViewById(R.id.q2_false);
        RadioButton q3_true = findViewById(R.id.q3_true);
        RadioButton q3_false = findViewById(R.id.q3_false);
        RadioButton q4_true = findViewById(R.id.q4_true);
        RadioButton q4_false = findViewById(R.id.q4_false);
        RadioButton q5_true = findViewById(R.id.q5_true);
        RadioButton q5_false = findViewById(R.id.q5_false);

        TextView major_qualify = findViewById(R.id.major_qualify_tv);
        major_qualify.setVisibility(View.INVISIBLE);
        TextView specialist_qualify = findViewById(R.id.specialist_qualify_tv);
        specialist_qualify.setVisibility(View.INVISIBLE);
        TextView both_qualify = findViewById(R.id.both_qualify_tv);
        both_qualify.setVisibility(View.INVISIBLE);
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

        q3_true.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    q3_flag = true;
                }
            }
        });

        q3_false.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    q3_flag = false;
                }
            }
        });

        q4_true.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    q4_flag = true;
                }
            }
        });

        q4_false.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    q4_flag = false;
                }
            }
        });

        q5_true.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    q5_flag = true;
                }
            }
        });

        q5_false.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    q5_flag = false;
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
                if (q1_flag && q2_flag && q3_flag && q4_flag && q5_flag) {
                    both_qualify.setVisibility(View.VISIBLE);
                    major_qualify.setVisibility(View.INVISIBLE);
                    specialist_qualify.setVisibility(View.INVISIBLE);
                    not_qualify.setVisibility(View.INVISIBLE);
                }
                else if (q1_flag && q2_flag && q3_flag) {
                    both_qualify.setVisibility(View.INVISIBLE);
                    major_qualify.setVisibility(View.VISIBLE);
                    specialist_qualify.setVisibility(View.INVISIBLE);
                    not_qualify.setVisibility(View.INVISIBLE);
                }
                else if (q1_flag && q4_flag && q5_flag) {
                    both_qualify.setVisibility(View.INVISIBLE);
                    major_qualify.setVisibility(View.INVISIBLE);
                    specialist_qualify.setVisibility(View.VISIBLE);
                    not_qualify.setVisibility(View.INVISIBLE);
                }
                else {
                    both_qualify.setVisibility(View.INVISIBLE);
                    major_qualify.setVisibility(View.INVISIBLE);
                    specialist_qualify.setVisibility(View.INVISIBLE);
                    not_qualify.setVisibility(View.VISIBLE);
                }

            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InMathPOStQualification.this, StudentHomePage.class);
                startActivity(intent);
            }
        });
    }
}