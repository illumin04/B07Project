package com.example.login_backend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class POStQualificationHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_qualification_home);
        Button cs = findViewById(R.id.cs);
        Button math = findViewById(R.id.math);
        Button stats = findViewById(R.id.stats);
        Button backButton = findViewById(R.id.backButton);

        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in_intent = new Intent(POStQualificationHome.this, CSPOStQualificationHome.class);
                startActivity(in_intent);
            }
        });

        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent out_intent = new Intent(POStQualificationHome.this, MathPOStQualificationHome.class);
                startActivity(out_intent);
            }
        });

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent out_intent = new Intent(POStQualificationHome.this, StatsPOStQualificationHome.class);
                startActivity(out_intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}