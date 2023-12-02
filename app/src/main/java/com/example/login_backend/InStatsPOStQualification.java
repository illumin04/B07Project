package com.example.login_backend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class InStatsPOStQualification extends AppCompatActivity {
    private boolean q1_flag = false;
    private boolean q2_flag = false;
    private boolean q3_flag = false;
    private boolean q4_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_stats_post_qualification);
        RadioButton q1_true = findViewById(R.id.q1_true);
        RadioButton q1_false = findViewById(R.id.q1_false);
        RadioButton q2_true = findViewById(R.id.q2_true);
        RadioButton q2_false = findViewById(R.id.q2_false);
        RadioButton q3_true = findViewById(R.id.q3_true);
        RadioButton q3_false = findViewById(R.id.q3_false);
        RadioButton q4_true = findViewById(R.id.q4_true);
        RadioButton q4_false = findViewById(R.id.q4_false);

        TextView major_qualify = findViewById(R.id.major_qualify_tv);
        TextView ml_specialist_qualify = findViewById(R.id.ml_specialist_qualify_tv);
        TextView other_specialist_qualify = findViewById(R.id.other_specialist_qualify_tv);
        TextView not_qualify = findViewById(R.id.not_qualify_tv);

        major_qualify.setVisibility(View.INVISIBLE);
        ml_specialist_qualify.setVisibility(View.INVISIBLE);
        other_specialist_qualify.setVisibility(View.INVISIBLE);
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
                if (q1_flag && q2_flag && q3_flag && q4_flag) {
                    major_qualify.setVisibility(View.VISIBLE);
                    other_specialist_qualify.setVisibility(View.VISIBLE);
                    ml_specialist_qualify.setVisibility(View.VISIBLE);
                    not_qualify.setVisibility(View.INVISIBLE);
                }
                else if (q1_flag && q2_flag && q3_flag) {
                    major_qualify.setVisibility(View.VISIBLE);
                    other_specialist_qualify.setVisibility(View.VISIBLE);
                    ml_specialist_qualify.setVisibility(View.INVISIBLE);
                    not_qualify.setVisibility(View.INVISIBLE);
                }
                else if (q1_flag && q2_flag) {
                    major_qualify.setVisibility(View.VISIBLE);
                    ml_specialist_qualify.setVisibility(View.INVISIBLE);
                    other_specialist_qualify.setVisibility(View.INVISIBLE);
                    not_qualify.setVisibility(View.INVISIBLE);
                }
                else if (q1_flag && q3_flag) {
                    if (q4_flag) {
                        major_qualify.setVisibility(View.INVISIBLE);
                        other_specialist_qualify.setVisibility(View.VISIBLE);
                        ml_specialist_qualify.setVisibility(View.VISIBLE);
                        not_qualify.setVisibility(View.INVISIBLE);
                    }
                    else {
                        major_qualify.setVisibility(View.INVISIBLE);
                        other_specialist_qualify.setVisibility(View.VISIBLE);
                        ml_specialist_qualify.setVisibility(View.INVISIBLE);
                        not_qualify.setVisibility(View.INVISIBLE);
                    }
                }
                else {
                    major_qualify.setVisibility(View.INVISIBLE);
                    other_specialist_qualify.setVisibility(View.INVISIBLE);
                    ml_specialist_qualify.setVisibility(View.INVISIBLE);
                    not_qualify.setVisibility(View.VISIBLE);
                }
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InStatsPOStQualification.this, StudentHomePage.class);
                startActivity(intent);
            }
        });

    }

}