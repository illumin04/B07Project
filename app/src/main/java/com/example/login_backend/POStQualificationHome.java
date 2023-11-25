package com.example.login_backend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class POStQualificationHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_qualification_home);
        RadioButton cs = findViewById(R.id.cs);
        RadioButton math = findViewById(R.id.math);
        RadioButton stats = findViewById(R.id.stats);

        cs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Intent in_intent = new Intent(POStQualificationHome.this, CSPOStQualificationHome.class);
                    startActivity(in_intent);
                }
            }
        });

        math.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Intent out_intent = new Intent(POStQualificationHome.this, MathPOStQualificationHome.class);
                    startActivity(out_intent);
                }
            }
        });

        stats.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Intent out_intent = new Intent(POStQualificationHome.this, StatsPOStQualificationHome.class);
                    startActivity(out_intent);
                }
            }
        });
    }
}