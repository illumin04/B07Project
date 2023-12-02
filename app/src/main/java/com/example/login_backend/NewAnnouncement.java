package com.example.login_backend;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewAnnouncement extends AppCompatActivity {

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_announcement);
        db = FirebaseDatabase.getInstance();
        EditText details = findViewById(R.id.details);
        Bundle extras = getIntent().getExtras();

        details.setSelection(0);
        // details.setText(); // backend pass string to setText()

        Button backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button submitButton = findViewById(R.id.submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newAnnouncement = details.getText().toString();
                submitNewAnnouncement(newAnnouncement);
                Toast.makeText(NewAnnouncement.this,
                        "Successfully created announcement", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void submitNewAnnouncement(String announcement){
        DatabaseReference ref = db.getReference().child("Announcements");
        DatabaseReference count = ref.child("announcementCount");
        count.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String countString = task.getResult().getValue().toString();
                int announcementCount = Integer.parseInt(countString);
                announcementCount += 1;
                String announcementName = "Announcement" + Integer.toString(announcementCount);
                ref.child("announcementCount").setValue(Integer.toString(announcementCount));
                ref.child(announcementName).setValue(announcement);
            }
        });
    }


}