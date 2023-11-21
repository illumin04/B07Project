package com.example.adminhomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

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
    }
}