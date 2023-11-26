package com.example.login_backend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewEvent extends AppCompatActivity {

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        db = FirebaseDatabase.getInstance();
        EditText event_name = findViewById(R.id.name);
        event_name.setSelection(0);
        // event_name.setText(); // backend pass string to setText()
        EditText event_details = findViewById(R.id.details);
        event_details.setSelection(0);
        // event_details.setText(); // backend pass string to setText()
        EditText limit = findViewById(R.id.stu_limit);
        // limit.setText(); // backend pass string to setText
        EditText eventMessage = findViewById(R.id.eventMessage);

        Button submitButton = findViewById(R.id.submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventName = event_name.getText().toString();
                String eventDetails = event_details.getText().toString();
                String eventLimit = limit.getText().toString();
                if(eventName.isEmpty()){
                    eventMessage.setText(R.string.eventMessage1);
                }else if(eventDetails.isEmpty()){
                    eventMessage.setText(R.string.eventMessage2);
                }else if(eventLimit.isEmpty()){
                    eventMessage.setText(R.string.eventMessage3);
                }else{
                    submitEvent(eventName, eventDetails, eventLimit);
                    finish();
                }
            }
        });
    }

    private void submitEvent(String name, String details, String limit){
        DatabaseReference ref = db.getReference().child("Events");
        ref.child(name).child("details").setValue(details);
        ref.child(name).child("limit").setValue(Integer.parseInt(limit));
        ref.child(name).child("rsvpCount").setValue(0);
        ref.child(name).child("rsvp").child("rsvpCount").setValue("0");
    }
}