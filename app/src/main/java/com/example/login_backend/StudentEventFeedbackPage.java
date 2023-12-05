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

        EditText feedback = findViewById(R.id.feedback);
        EditText ratings = findViewById(R.id.scale);
        Bundle extras = getIntent().getExtras();
        db = FirebaseDatabase.getInstance();

        String eventName = null;
        String username = null;
        if(extras != null){
            eventName = extras.getString("eventName");
            username = extras.getString("username");
        }

        showInfo(eventName);



        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = feedback.getText().toString();
                String rating =  ratings.getText().toString();
                String eventName = null;
                String username = null;
                if(extras != null){
                    eventName = extras.getString("eventName");
                    username = extras.getString("username");
                }
                if(comment.equals("")){
                    Toast.makeText(StudentEventFeedbackPage.this,
                            "Please provide comments!", Toast.LENGTH_SHORT).show();
                }else if(rating.equals("")){
                    Toast.makeText(StudentEventFeedbackPage.this,
                            "Please provide your rating!", Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(rating) < 0 || Integer.parseInt(rating) > 100){
                    Toast.makeText(StudentEventFeedbackPage.this,
                            "Please provide your rating from 0 to 100!", Toast.LENGTH_SHORT).show();
                } else{
                    int scale = Integer.parseInt(rating);
                    submitRating(eventName, scale);
                    submitComment(eventName, username, comment);
                    finish();
                    Toast.makeText(StudentEventFeedbackPage.this,
                            "Successfully submitted feedback!", Toast.LENGTH_SHORT).show();
                }


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

    private void submitRating(String eventName, int rating){
        DatabaseReference ref = db.getReference().child("Events")
                .child(eventName).child("ratings");
        ref.child("rateCount").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                int count = Integer.parseInt(task.getResult().getValue(String.class));
                ref.child("rateCount").setValue(Integer.toString(count+1));
            }
        });
        ref.child("totalRate").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        int totalRate = Integer.parseInt(task.getResult().getValue(String.class));
                        ref.child("totalRate")
                                .setValue(Integer.toString(totalRate + rating));
                    }
                });
    }


    private void submitComment(String eventName, String username, String comment){
        DatabaseReference ref = db.getReference().child("Events").child(eventName)
                .child("comments").child("commentCount");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                int count = Integer.parseInt(task.getResult().getValue(String.class));
                count += 1;
                ref.setValue(Integer.toString(count));
                String commentName = "comment" + count;
                addComment(eventName, username, commentName, comment);
            }
        });
    }

    private void addComment(String eventName, String username, String commentName, String comment){
        DatabaseReference ref = db.getReference().child("Events").child(eventName)
                .child("comments").child(commentName);
        ref.child("content").setValue(comment);
        ref.child("username").setValue(username);
    }

    private void showInfo(String eventName){
        showEventName(eventName);
        showEventDetails(eventName);
        showEventTime(eventName);
    }

    private void showEventTime(String eventName){
        DatabaseReference ref = db.getReference().child("Events")
                .child(eventName).child("time");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String time = "Event scheduled time: " + task.getResult().getValue(String.class);
                TextView event_time = findViewById(R.id.time);
                event_time.setText(time);
            }
        });
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