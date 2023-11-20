package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ComplaintsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        Button newComplaintsButton = findViewById(R.id.newComplaintsButton);
        Button viewComplaintsButton = findViewById(R.id.viewComplaintsButton);
        Button backButton = findViewById(R.id.backButton);

        newComplaintsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start NewComplaintsActivity
                Intent intent = new Intent(ComplaintsActivity.this, NewComplaintsActivity.class);
                startActivity(intent);
            }
        });

        viewComplaintsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ViewComplaintsActivity
                Intent intent = new Intent(ComplaintsActivity.this, ViewComplaintsActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the current activity and go back
                finish();
            }
        });
    }
}
