package com.example.login_backend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EventPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        Bundle extras = getIntent().getExtras();

        Button event1 = findViewById(R.id.event1);
        event1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventPage.this, FeedbackSummaryPage.class);
                String username = null;
                if (extras != null) {
                    username = extras.getString("username");
                }
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        // more buttons to be added

        Button backButton = findViewById(R.id.back_btn);
        Button newEventButton = findViewById(R.id.new_event_btn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        newEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventPage.this, NewEvent.class);
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