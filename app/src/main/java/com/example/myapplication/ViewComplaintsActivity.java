package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewComplaintsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaints);

        TextView complaintsTextView = findViewById(R.id.complaintsTextView);
        Button backButton = findViewById(R.id.backButton);

        // Retrieve and display complaints (replace this with actual logic)
        String allComplaints = "Complaint 1\nComplaint 2\nComplaint 3";
        complaintsTextView.setText(allComplaints);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the current activity and go back
                finish();
            }
        });
    }
}
