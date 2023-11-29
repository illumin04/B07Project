package com.example.login_backend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentEventFeedbackPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_event_feedback_page);
        EditText event_name = findViewById(R.id.name);
        // event_name.setText(); // backend pass string to setText()
        EditText event_details = findViewById(R.id.details);
        // event_details.setText(); // backend pass string to setText()
        TextView feedback = findViewById(R.id.feedback);
        // pass string to backend
        EditText ratings = findViewById(R.id.scale);
        // pass string to backend
        Bundle extras = getIntent().getExtras();

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StudentEventFeedbackPage.this,
                        "Successfully submitted feedback!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StudentEventFeedbackPage.this, ViewEvent.class);
                String username = null;
                if (extras != null) {
                    username = extras.getString("username");
                }
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}