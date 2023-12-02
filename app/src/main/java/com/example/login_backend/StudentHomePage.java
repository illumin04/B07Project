package com.example.login_backend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentHomePage extends AppCompatActivity {

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);
        db = FirebaseDatabase.getInstance();

        Button postQualificationButton = findViewById(R.id.POSt);
        Button complaintsButton = findViewById(R.id.Complaint);
        Button announcementsButton = findViewById(R.id.Announcement);
        Button eventsButton = findViewById(R.id.Event);
        Bundle extras = getIntent().getExtras();
        String username = null;
        if(extras != null){
            username = extras.getString("username");
        }
        checkAnnouncement(username);

        postQualificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomePage.this, POStQualificationHome.class);
                Bundle extras = getIntent().getExtras();
                String username = null;
                if (extras != null) {
                    username = extras.getString("username");
                }
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        complaintsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomePage.this, ViewComplaints.class);
                String username = null;
                if (extras != null) {
                    username = extras.getString("username");
                }
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        announcementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomePage.this, ViewAnnouncements.class);
                String username = null;
                if (extras != null) {
                    username = extras.getString("username");
                }
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        eventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentHomePage.this, ViewEvent.class);
                String username = null;
                if (extras != null) {
                    username = extras.getString("username");
                }
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }


    private void checkAnnouncement(String username){
        DatabaseReference ref = db.getReference().child("Announcements")
                .child("announcementCount");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int annCount = Integer.parseInt(snapshot.getValue(String.class));
                checkViewed(annCount, username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void checkViewed(int annCount, String username){
        DatabaseReference ref = db.getReference().child("Users")
                .child(username).child("announcementViewed");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TextView msg = (TextView) findViewById(R.id.annMsg);
                int annViewed = Integer.parseInt(snapshot.getValue(String.class));
                if(annViewed == annCount){
                    msg.setText("");
                }else{
                    int unread = annCount - annViewed;
                    String text = "You have " + unread + " unread announcements!!";
                    msg.setText(text);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
