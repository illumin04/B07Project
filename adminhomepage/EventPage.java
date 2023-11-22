package com.example.adminhomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class EventPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        Button event1 = findViewById(R.id.event1);
        event1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventPage.this, FeedbackSummaryPage.class);
                startActivity(intent);
            }
        });

        Button event2 = findViewById(R.id.event2);
        event2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventPage.this, FeedbackSummaryPage.class);
                startActivity(intent);
            }
        });

        Button event3 = findViewById(R.id.event3);
        event3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventPage.this, FeedbackSummaryPage.class);
                startActivity(intent);
            }
        });

        Button backButton = findViewById(R.id.back_btn);
        Button newEventButton = findViewById(R.id.new_event_btn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        newEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventPage.this, NewEvent.class);
                startActivity(intent);
            }
        });
    }
}