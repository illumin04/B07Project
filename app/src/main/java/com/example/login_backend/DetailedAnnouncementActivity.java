package com.example.login_backend;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailedAnnouncementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_announcement);

        TextView detailedAnnouncementTextView = findViewById(R.id.detailedAnnouncementTextView);
        Button rsvpButton = findViewById(R.id.rsvpButton);
        Button commentRatingButton = findViewById(R.id.commentRatingButton);
        Button backButton = findViewById(R.id.backButton);

        // Retrieve the announcement content from the intent
        String announcementContent = getIntent().getStringExtra("announcementContent");
        detailedAnnouncementTextView.setText(announcementContent);

        rsvpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle RSVP button click
                // Implement RSVP logic here
            }
        });

        commentRatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Comment/Rating button click
                // Implement Comment/Rating logic here
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
