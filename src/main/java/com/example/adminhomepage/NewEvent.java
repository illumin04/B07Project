package com.example.adminhomepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.*;
import android.content.Intent;
import android.os.*;
import android.view.View;
import android.widget.*;

public class NewEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        EditText event_name = findViewById(R.id.name);
        event_name.setSelection(0);
        // event_name.setText(); // backend pass string to setText()
        EditText event_details = findViewById(R.id.details);
        event_details.setSelection(0);
        // event_details.setText(); // backend pass string to setText()
        EditText limit = findViewById(R.id.stu_limit);
        // limit.setText(); // backend pass string to setText

        Button submitButton = findViewById(R.id.submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewEvent.this, EventPage.class);
                startActivity(intent);
            }
        });
    }
}