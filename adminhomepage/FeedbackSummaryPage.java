package com.example.adminhomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class FeedbackSummaryPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_summary_page);
        TextView event_details = findViewById(R.id.details);
        // event_details.setText(); // backend pass string to setText()
        TextView student_comments = findViewById(R.id.comments);
        // student_comments.setText(); // backend pass string to setText()
        EditText ratings = findViewById(R.id.ratings);
        // ratings.setText(); // backend pass string to setText()

        Button backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeedbackSummaryPage.this, EventPage.class);
                startActivity(intent);
            }
        });
    }
}