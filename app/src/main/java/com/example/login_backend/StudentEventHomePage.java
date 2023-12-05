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

public class StudentEventHomePage extends AppCompatActivity {

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_event_home_page);
        Bundle extras = getIntent().getExtras();
        db = FirebaseDatabase.getInstance();

        String eventName = null;
        if(extras != null){
            eventName = extras.getString("eventName");
        }

        //show detailed event Information on the page
        showInfo(eventName);
        DatabaseReference ref = db.getReference().child("Events").child(eventName);


        Button rsvp = findViewById(R.id.rsvpButton);
        rsvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = null;
                if (extras != null) {
                    username = extras.getString("username");
                    DatabaseReference userReg = ref.child("rsvp").child(username);
                    userReg.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if(task.getResult().exists()){
                                Toast.makeText(StudentEventHomePage.this,
                                        "Already registered!", Toast.LENGTH_SHORT).show();
                            }else{
                                String username = extras.getString("username");
                                String eventName = extras.getString("eventName");
                                TextView limit_view = findViewById(R.id.rsvpLimit);
                                Rsvp(username, eventName,
                                        Integer.parseInt(limit_view.getText()
                                                .toString()));
                            }
                        }
                    });
                }

            }
        });

        Button submit_redirect = findViewById(R.id.submit_area);
        submit_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentEventHomePage.this,
                        StudentEventFeedbackPage.class);
                String username = null;
                String eventName = null;
                if (extras != null) {
                    username = extras.getString("username");
                    eventName = extras.getString("eventName");
                }
                intent.putExtra("username", username);
                intent.putExtra("eventName", eventName);
                startActivity(intent);
            }
        });

        Button backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showInfo(String eventName){
        showEventName(eventName);
        showEventDetails(eventName);
        showEventTime(eventName);
        showEventLimit(eventName);
        showEventReg(eventName);
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

    private void showEventLimit(String eventName){
        DatabaseReference ref = db.getReference().child("Events")
                .child(eventName).child("limit");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String limit = task.getResult().getValue(String.class);
                TextView event_name = findViewById(R.id.rsvpLimit);
                event_name.setText(limit);
            }
        });
    }

    private void showEventReg(String eventName){
        DatabaseReference ref = db.getReference().child("Events")
                .child(eventName).child("rsvp").child("rsvpCount");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String rsvpCount = task.getResult().getValue(String.class);
                TextView event_name = findViewById(R.id.currentReg);
                event_name.setText(rsvpCount);
            }
        });
    }

    private void Rsvp(String username, String eventName, int limit){
        DatabaseReference ref = db.getReference().child("Events")
                .child(eventName).child("rsvp").child("rsvpCount");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                int rsvpCount = Integer.parseInt(task.getResult().getValue(String.class));
                if(rsvpCount >= limit){
                    RegisterFailMessage();
                }else{
                    Register(username, eventName);
                }
            }
        });
    }

    private void Register(String username, String eventName){
        DatabaseReference ref = db.getReference().child("Events")
                .child(eventName).child("rsvp");
        ref.child(username).setValue(username);
        ref.child("rsvpCount").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        int count = Integer.parseInt(task.getResult().getValue(String.class));
                        count += 1;
                        ref.child("rsvpCount").setValue(Integer.toString(count));
                        TextView current = findViewById(R.id.currentReg);
                        String update = Integer.toString(count);
                        current.setText(update);
                        SuccessfullyRegistered();
                    }
                });
    }

    private void RegisterFailMessage(){
        Toast.makeText(StudentEventHomePage.this,
                "Event capacity full, cannot register!", Toast.LENGTH_SHORT).show();
    }


    private void SuccessfullyRegistered(){
        Toast.makeText(StudentEventHomePage.this,
                "Successfully registered for the event!", Toast.LENGTH_SHORT).show();
    }
}