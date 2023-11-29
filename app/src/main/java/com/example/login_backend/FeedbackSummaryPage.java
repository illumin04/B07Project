package com.example.login_backend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FeedbackSummaryPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_summary_page);
        EditText event_name = findViewById(R.id.name);
        // event_name.setText(); // backend pass string to setText()
        EditText event_details = findViewById(R.id.details);
        // event_details.setText(); // backend pass string to setText()
        TextView feedback = findViewById(R.id.feedback);
        // student_comments.setText(); // backend pass string to setText()
        EditText ratings = findViewById(R.id.ratings);
        // ratings.setText(); // backend pass string to setText()
        Bundle extras = getIntent().getExtras();

        Button backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeedbackSummaryPage.this, EventPage.class);
                String username = null;
                if (extras != null) {
                    username = extras.getString("username");
                }
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}