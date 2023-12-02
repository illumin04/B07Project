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

public class EventPage extends AppCompatActivity {

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        db = FirebaseDatabase.getInstance();
        Bundle extras = getIntent().getExtras();

        setLayout();

        // more buttons to be added

        Button backButton = findViewById(R.id.back_btn);
        Button newEventButton = findViewById(R.id.new_event_btn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        newEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventPage.this, NewEvent.class);
                String username = null;
                if (extras != null) {
                    username = extras.getString("username");
                }
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }

    private void setLayout(){
        DatabaseReference ref = db.getReference().child("Events")
                .child("eventCount");
        LinearLayout ll = (LinearLayout) findViewById(R.id.eventLayout);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ll.removeAllViews();
                createLayout();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void createLayout(){
        LinearLayout ll = findViewById(R.id.eventLayout);
        DatabaseReference ref = db.getReference().child("Events")
                .child("eventCount");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                int count = Integer.parseInt(task.getResult().getValue(String.class));
                for(int i=count;i>=1;--i){
                    addEvent(ll, i);
                }
            }
        });
    }

    private void addEvent(LinearLayout ll, int eventId){
        String eventName = "event" + eventId;
        addButton(ll, eventName);
    }


    private void addButton(LinearLayout ll, String eventName){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        Button btn = new Button(EventPage.this);
        params.setMargins(10,15,10,15);
        int buttonId = Integer.parseInt(eventName.substring(5));
        btn.setLayoutParams(params);
        btn.setId(buttonId);
        btn.setTextColor(Color.WHITE);
        btn.setBackgroundColor(Color.rgb(70,80,90));
        setButtonText(eventName, buttonId);
        btn.setTextSize(20);
        ll.addView(btn, params);
        setButton(eventName, buttonId);
    }


    private void setButtonText(String eventName, int buttonId){
        DatabaseReference ref = db.getReference().child("Events")
                .child(eventName).child("name");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Button btn = (Button) findViewById(buttonId);
                String name = task.getResult().getValue(String.class);
                btn.setText(name);
            }
        });
    }

    private void setButton(String eventName, int buttonId){
        Button btn = (Button) findViewById(buttonId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventPage.this, FeedbackSummaryPage.class);
                Bundle extras = getIntent().getExtras();
                String username = null;
                if(extras != null){
                    username = extras.getString("username");
                }
                intent.putExtra("username", username);
                intent.putExtra("eventName", eventName);
                startActivity(intent);
            }
        });
    }
}