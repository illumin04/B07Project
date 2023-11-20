package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewAnnouncementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_announcements);

        TextView announcementsTextView = findViewById(R.id.announcementsTextView);
        Button backButton = findViewById(R.id.backButton);

        // Retrieve and display announcements (replace this with actual logic)
        String allAnnouncements = "123123\n123123\n123123";
        announcementsTextView.setText(allAnnouncements);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the current activity and go back
                finish();
            }
        });

        // Set click listener for the announcements
        announcementsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DetailedAnnouncementActivity (pass the clicked announcement as an extra)
                Intent intent = new Intent(ViewAnnouncementsActivity.this, DetailedAnnouncementActivity.class);
                intent.putExtra("announcementContent", ((TextView) v).getText().toString());
                startActivity(intent);
            }
        });
    }
}
