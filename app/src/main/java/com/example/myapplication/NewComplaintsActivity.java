package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewComplaintsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_complaints);

        EditText complaintMessageEditText = findViewById(R.id.complaintMessageEditText);
        Button backButton = findViewById(R.id.backButton);
        Button submitButton = findViewById(R.id.submitButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the current activity and go back
                finish();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the complaint message and handle submission logic
                String complaintMessage = complaintMessageEditText.getText().toString();
                // Submit to the database
            }
        });
    }
}
