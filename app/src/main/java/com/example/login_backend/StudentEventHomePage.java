package com.example.login_backend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentEventHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_event_home_page);
        EditText event_name = findViewById(R.id.name);
        // event_name.setText(); // backend pass string to setText()
        EditText event_details = findViewById(R.id.details);
        // event_details.setText(); // backend pass string to setText()
        Bundle extras = getIntent().getExtras();

        Button rsvp = findViewById(R.id.rsvpButton);
        rsvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StudentEventHomePage.this,
                        "Successfully registered for the event!", Toast.LENGTH_SHORT).show();
                // backend add 1 person to existing event students
            }
        });

        Button submit_redirect = findViewById(R.id.submit_area);
        submit_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentEventHomePage.this,
                        StudentEventFeedbackPage.class);
                String username = null;
                if (extras != null) {
                    username = extras.getString("username");
                }
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        Button backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}