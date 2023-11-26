package com.example.login_backend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class CSPOStQualificationHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cspost_qualification_home);

        Button yes = findViewById(R.id.yes);
        Button no = findViewById(R.id.no);
        Button backButton = findViewById(R.id.backButton);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in_intent = new Intent(CSPOStQualificationHome.this, InCSPOStQualification.class);
                startActivity(in_intent);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent out_intent = new Intent(CSPOStQualificationHome.this, OutCSPOStQualification.class);
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