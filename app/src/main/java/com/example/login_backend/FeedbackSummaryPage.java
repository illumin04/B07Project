package com.example.login_backend;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedbackSummaryPage extends AppCompatActivity {
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_summary_page);
        db = FirebaseDatabase.getInstance();
        Bundle extras = getIntent().getExtras();
        String eventName = null;
        if(extras != null){
            eventName = extras.getString("eventName");
        }

        Button backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        showInfo(eventName);
    }


    private void showInfo(String eventName){
        showEventName(eventName);
        showEventDetails(eventName);
        showEventTime(eventName);
        setFeedbacks(eventName);
        setRating(eventName);
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


    private void setFeedbacks(String eventName){
        DatabaseReference ref = db.getReference().child("Events").child(eventName)
                .child("comments").child("commentCount");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                LinearLayout ll = (LinearLayout) findViewById(R.id.feedbackLayout);
                ll.removeAllViews();
                showFeedbacks(eventName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void setRating(String eventName){
        DatabaseReference ref = db.getReference().child("Events").child(eventName)
                .child("ratings").child("rateCount");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showRating(eventName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void showRating(String eventName){
        DatabaseReference ref = db.getReference().child("Events").child(eventName)
                .child("ratings").child("rateCount");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                double count = Double.parseDouble(task.getResult().getValue(String.class));
                if(count == 0){
                    ShowNoRate();
                }else{
                    showAverage(eventName, count);
                }
            }
        });
    }


    private void showAverage(String eventName, double count){
        DatabaseReference ref = db.getReference().child("Events").child(eventName)
                .child("ratings").child("totalRate");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                double total = Double.parseDouble(task.getResult().getValue(String.class));
                double average = total/count;
                setAverage(average);
                setCount(count);
            }
        });
    }


    private void setAverage(double average){
        TextView rating = (TextView) findViewById(R.id.ratings);
        rating.setText(Double.toString(average));
    }

    private void setCount(double count){
        int c = (int) count;
        String msg = "Total count of feedbacks: ";
        TextView countMsg = (TextView) findViewById(R.id.countMsg);
        TextView countView = (TextView) findViewById(R.id.feedbackCount);
        countMsg.setText(msg);
        countView.setText(Integer.toString(c));
    }

    private void ShowNoRate(){
        TextView rating = (TextView) findViewById(R.id.ratings);
        rating.setText("Currently no rating!");
    }


    private void showFeedbacks(String eventName){
        DatabaseReference ref = db.getReference().child("Events")
                .child(eventName).child("comments").child("commentCount");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                int count = Integer.parseInt(task.getResult().getValue(String.class));
                if(count == 0){
                    showNoFeedbacks();
                }else{
                    for (int i=count;i>=1;--i){
                        addFeedback(eventName, i);
                    }
                }
            }
        });
    }


    private void showNoFeedbacks(){
        LinearLayout ll = (LinearLayout) findViewById(R.id.feedbackLayout);
        TextView newView = new TextView(FeedbackSummaryPage.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        newView.setLayoutParams(params);
        String text = "Currently no feedback!";
        newView.setText(text);
        newView.setTextSize(20);
        newView.setTextColor(Color.rgb(90,90,90));
        ll.addView(newView);
    }

    private void addFeedback(String eventName, int commentId){
        addUserName(eventName, commentId);
    }


    private void addUserName(String eventName, int commentId){
        String commentName = "comment" + commentId;
        DatabaseReference ref = db.getReference().child("Events")
                .child(eventName).child("comments")
                .child(commentName).child("username");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String username = task.getResult().getValue(String.class);
                addContent(eventName, commentId, username);
            }
        });
    }


    private void addContent(String eventName, int commentId, String username){
        String commentName = "comment" + commentId;
        DatabaseReference ref = db.getReference().child("Events")
                .child(eventName).child("comments")
                .child(commentName).child("content");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String content = task.getResult().getValue(String.class);
                addComment(username, content);
            }
        });
    }


    private void addComment(String username, String content){
        LinearLayout ll = (LinearLayout) findViewById(R.id.feedbackLayout);
        TextView comment = new TextView(FeedbackSummaryPage.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        comment.setLayoutParams(params);
        String text = username + ":  " + content;
        comment.setText(text);
        comment.setTextSize(20);
        comment.setTextColor(Color.rgb(90,90,90));
        ll.addView(comment);
    }
}