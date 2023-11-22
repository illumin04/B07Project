package com.example.adminhomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class ComplaintsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_page);

        Button complaint1 = findViewById(R.id.complaint1);
        complaint1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComplaintsPage.this, ComplaintDetailsPage.class);
                startActivity(intent);
            }
        });

        Button complaint2 = findViewById(R.id.complaint2);
        complaint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComplaintsPage.this, ComplaintDetailsPage.class);
                startActivity(intent);
            }
        });

        Button complaint3 = findViewById(R.id.complaint3);
        complaint3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComplaintsPage.this, ComplaintDetailsPage.class);
                startActivity(intent);
            }
        });

        // more buttons to be added

        Button backbutton = findViewById(R.id.back_btn);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComplaintsPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}