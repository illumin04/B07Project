package com.example.login_backend;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PostQualificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_qualification);

        EditText answer1EditText = findViewById(R.id.answer1EditText);
        EditText answer2EditText = findViewById(R.id.answer2EditText);
        EditText answer3EditText = findViewById(R.id.answer3EditText);
        Button checkButton = findViewById(R.id.checkButton);
        TextView resultTextView = findViewById(R.id.resultTextView);
        Button backButton = findViewById(R.id.backButton);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check answers (for now, consider all answers as correct)
                String answer1 = answer1EditText.getText().toString();
                String answer2 = answer2EditText.getText().toString();
                String answer3 = answer3EditText.getText().toString();

                // For now, consider all answers as correct
                boolean allCorrect = true;

                // Replace the following logic with actual checking logic
                if (answer1.isEmpty() || answer2.isEmpty() || answer3.isEmpty()) {
                    allCorrect = false;
                }

                // Display result message
                if (allCorrect) {
                    resultTextView.setText("Qualified");
                } else {
                    resultTextView.setText("Not qualified");
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

    }
}
