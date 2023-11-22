package com.example.adminhomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComplaintDetailsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details_page);

        Button backbutton = findViewById(R.id.back_btn);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComplaintDetailsPage.this, ComplaintsPage.class);
                startActivity(intent);
            }
        });
    }
}