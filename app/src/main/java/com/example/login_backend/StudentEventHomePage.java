package com.example.login_backend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentEventHomePage extends AppCompatActivity {

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_event_home_page);
        Bundle extras = getIntent().getExtras();
        db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference();
//        String eventName = null;
//        if(extras != null){
//            eventName = extras.getString("eventName");
//        }

        //show detailed event Information on the page
//        showInfo(eventName);


        Button rsvp = findViewById(R.id.rsvpButton);
        rsvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = null;
                if (extras != null) {
                    username = extras.getString("username");

                }

                Toast.makeText(StudentEventHomePage.this,
                        "Cannot register for the event!", Toast.LENGTH_SHORT).show();

                Toast.makeText(StudentEventHomePage.this,
                        "Successfully registered for the event!", Toast.LENGTH_SHORT).show();

            }
        });

        Button submit_redirect = findViewById(R.id.submit_area);
        submit_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentEventHomePage.this,
                        StudentEventFeedbackPage.class);
                String username = null;
                if (extras != null) {
                    username = extras.getString("username");
                }
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        Button backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showInfo(String eventName){
        showEventName(eventName);
        showEventDetails(eventName);
        showEventLimit(eventName);
        showEventReg(eventName);
    }


    private void showEventName(String eventName){
        DatabaseReference ref = db.getReference().child("Events")
                .child(eventName).child("name");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String name = task.getResult().getValue(String.class);
                TextView event_name = findViewById(R.id.name);
                event_name.setText(name);
            }
        });
    }

    private void showEventDetails(String eventName){
        DatabaseReference ref = db.getReference().child("Events")
                .child(eventName).child("details");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String details = task.getResult().getValue(String.class);
                TextView event_name = findViewById(R.id.details);
                event_name.setText(details);
            }
        });
    }

    private void showEventLimit(String eventName){
        DatabaseReference ref = db.getReference().child("Events")
                .child(eventName).child("limit");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String limit = "Event limit of registration: " +
                    task.getResult().getValue(String.class);
                TextView event_name = findViewById(R.id.rsvpLimit);
                event_name.setText(limit);
            }
        });
    }

    private void showEventReg(String eventName){
        DatabaseReference ref = db.getReference().child("Events")
                .child(eventName).child("rsvp").child("rsvpCount");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String rsvpCount = "Current Registered count: "
                        + task.getResult().getValue(String.class);
                TextView event_name = findViewById(R.id.currentReg);
                event_name.setText(rsvpCount);
            }
        });
    }
}