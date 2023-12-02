package com.example.login_backend;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AnnouncementPage extends AppCompatActivity {
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_page);
        Bundle extras = getIntent().getExtras();
        db = FirebaseDatabase.getInstance();
        DatabaseReference AnnouncementCountRef = db.getReference()
                        .child("Announcements").child("announcementCount");
        AnnouncementCountRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                LinearLayout ll = (LinearLayout) findViewById(R.id.announcementLayout);
                ll.removeAllViews();
                createLayout();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button backbutton = findViewById(R.id.back_btn);
        Button newAnnouncementButton = findViewById(R.id.new_announcement_btn);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        newAnnouncementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnnouncementPage.this, NewAnnouncement.class);

                String username = null;
                if (extras != null) {
                    username = extras.getString("username");
                }
                intent.putExtra("username", username);
                startActivity(intent);

            }
        });
    }
    private void createLayout(){
        LinearLayout ll = findViewById(R.id.announcementLayout);
        DatabaseReference ref = db.getReference().child("Announcements")
                .child("announcementCount");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                int count = Integer.parseInt(task.getResult().getValue(String.class));
                for(int i=count;i>=1;--i){
                    addAnnouncement(ll, i);
                }
            }
        });
    }

    private void addAnnouncement(LinearLayout ll, int announcementId){
        String announcementName = "Announcement" + announcementId;
        DatabaseReference ref = db.getReference().child("Announcements")
                .child(announcementName);
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String content = task.getResult().getValue(String.class);
                addTextView(ll, content);

            }
        });
    }


    private void addTextView(LinearLayout ll, String content){
        TextView newAnnouncement = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        newAnnouncement.setLayoutParams(params);
        newAnnouncement.setText(content + "\n");
        newAnnouncement.setTextSize(20);
        newAnnouncement.setTextColor(Color.rgb(100,100,100));
        ll.addView(newAnnouncement);
    }
}