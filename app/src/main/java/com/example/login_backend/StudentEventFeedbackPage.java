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

public class StudentEventFeedbackPage extends AppCompatActivity {
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_event_feedback_page);

        TextView feedback = findViewById(R.id.feedback);
        // pass string to backend
        EditText ratings = findViewById(R.id.scale);
        // pass string to backend
        Bundle extras = getIntent().getExtras();
        db = FirebaseDatabase.getInstance();

        String eventName = null;
        if(extras != null){
            eventName = extras.getString("eventName");
        }

        showInfo(eventName);


        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StudentEventFeedbackPage.this,
                        "Successfully submitted feedback!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StudentEventFeedbackPage.this, ViewEvent.class);
                String username = null;
                if (extras != null) {
                    username = extras.getString("username");
                }
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        Button backButton = findViewById(R.id.backButton);
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
}