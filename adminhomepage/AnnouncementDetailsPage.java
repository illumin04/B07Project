package com.example.adminhomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AnnouncementDetailsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_details_page);
        EditText details = findViewById(R.id.details);
        // ratings.setText(); // backend pass string to setText()

        Button backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnnouncementDetailsPage.this, AnnouncementPage.class);
                startActivity(intent);
            }
        });
    }
}