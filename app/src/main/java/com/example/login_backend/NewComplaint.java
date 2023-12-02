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

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewComplaint extends AppCompatActivity {

    EditText complaintMessageEditText;
    FirebaseDatabase db;

    private void getComplainText() {
        complaintMessageEditText = findViewById(R.id.details);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_complaint);
        getComplainText();
        Button backButton = findViewById(R.id.backButton);
        Button submitButton = findViewById(R.id.submitButton);

        db = FirebaseDatabase.getInstance();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the current activity and go back
                GoBack();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitComplaint();
                Toast.makeText(NewComplaint.this, "Successfully submitted complaint", Toast.LENGTH_SHORT).show();
                GoBack();
            }
        });
    }

    public void GoBack() {
        finish();
    }

    public void submitComplaint() {
        // Get the complaint message and handle submission logic
        String complaintMessage = complaintMessageEditText.getText().toString();
        // Submit to the database
        submit(complaintMessage);

    }

    private void submit(String complaint) {
        DatabaseReference ref = db.getReference();
        DatabaseReference query = ref.child("Complaints");
        DatabaseReference count = query.child("complaintCount");
        count.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String complaintCountstring = task.getResult().getValue().toString();
                int complaintCount = Integer.parseInt(complaintCountstring);
                Bundle extras = getIntent().getExtras();
                String username = null;
                if(extras != null){
                    username = extras.getString("username");
                }
                complaintCount += 1;
                String ComplaintName = "complaint" + complaintCount;
                complaintCountstring = Integer.toString(complaintCount);
                String timeInfo = getTime() + "\n    ";
                query.child("complaintCount").setValue(complaintCountstring);
                query.child(ComplaintName).child("content").setValue(complaint);
                query.child(ComplaintName).child("username").setValue(username);
                query.child(ComplaintName).child("time").setValue(timeInfo);
            }
        });

    }


    private String getTime(){
        Date time = Calendar.getInstance().getTime();
        String dateFormat = DateFormat.getDateInstance(DateFormat.SHORT).format(time);
        String timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT).format(time);
        return dateFormat + " " + timeFormat;
    }
}