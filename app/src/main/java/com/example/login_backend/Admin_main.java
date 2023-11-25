package com.example.login_backend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Button announcementButton = findViewById(R.id.announcements_btn);
        Button eventButton = findViewById(R.id.events_btn);
        Button complaintsButton = findViewById(R.id.complaints_btn);

        announcementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_main.this, AnnouncementPage.class);
                startActivity(intent);
            }
        });


        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_main.this, EventPage.class);
                startActivity(intent);
            }
        });


        complaintsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_main.this, ComplaintsPage.class);
                startActivity(intent);
            }
        });
    }
}