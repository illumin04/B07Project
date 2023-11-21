package com.example.adminhomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class AnnouncementPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_page);
        Button backbutton = findViewById(R.id.back_btn);
        Button newAnnouncementButton = findViewById(R.id.new_announcement_btn);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnnouncementPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        newAnnouncementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnnouncementPage.this, NewAnnouncement.class);
                startActivity(intent);
            }
        });
    }
}