package com.example.login_backend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AnnouncementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        Button viewAnnouncementsButton = findViewById(R.id.viewAnnouncementsButton);
        Button backButton = findViewById(R.id.backButton);

        viewAnnouncementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ViewAnnouncementsActivity
                Intent intent = new Intent(AnnouncementsActivity.this, ViewAnnouncementsActivity.class);
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
