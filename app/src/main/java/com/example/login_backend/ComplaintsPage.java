package com.example.login_backend;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ComplaintsPage extends AppCompatActivity {
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_page);
        Bundle extras = getIntent().getExtras();
        db = FirebaseDatabase.getInstance();

        createLayout();

        Button backbutton = findViewById(R.id.back_btn);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void createLayout(){
        DatabaseReference ref = db.getReference().child("Complaints")
                .child("complaintCount");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = Integer.parseInt(snapshot.getValue(String.class));
                LinearLayout ll = (LinearLayout) findViewById(R.id.complaintLayout2);
                ll.removeAllViews();
                showComplaints(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void showComplaints(int count){
        for(int i=count;i>=1;--i){
            addComplaint(i);
        }
    }


    private void addComplaint(int complaintId){
        String complaintName = "complaint" + complaintId;
        DatabaseReference ref = db.getReference().child("Complaints")
                .child(complaintName).child("username");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String complaintName = "complaint" + complaintId;
                String username = task.getResult().getValue(String.class);
                addContent(complaintName, username);

            }
        });
    }


    private void addContent(String complaintName, String username){
        DatabaseReference ref = db.getReference().child("Complaints")
                .child(complaintName).child("content");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String content = task.getResult().getValue(String.class);
                addTime(complaintName, username, content);
            }
        });
    }

    private void addTime(String complaintName, String username, String content){
        DatabaseReference ref = db.getReference().child("Complaints")
                .child(complaintName).child("time");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String time = task.getResult().getValue(String.class);
                addText(username, content, time);
            }
        });
    }

    private void addText(String username, String content, String time){
        LinearLayout ll = (LinearLayout) findViewById(R.id.complaintLayout2);
        String text = time + username + ": " + content;
        TextView textView = new TextView(ComplaintsPage.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.setMargins(10,15,10,15);
        textView.setLayoutParams(params);
        textView.setText(text);
        textView.setTextSize(20);
        textView.setBackgroundColor(Color.rgb(70, 80, 90));
        ll.addView(textView);
    }
}