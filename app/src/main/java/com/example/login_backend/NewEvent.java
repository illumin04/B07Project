package com.example.login_backend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
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
//        limit.setSelection(14);
        EditText eventMessage = findViewById(R.id.eventMessage);

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

    private void submitEvent(String eventName, String details, String limit){
        DatabaseReference ref = db.getReference().child("Events");
        DatabaseReference countRef = ref.child("eventCount");
        countRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                int c = Integer.parseInt(task.getResult().getValue().toString());
                c += 1;
                String name = "event" + c;
                ref.child(name).child("details").setValue(details);
                ref.child(name).child("limit").setValue(limit);
                ref.child(name).child("rsvp").child("rsvpCount").setValue("0");
                ref.child(name).child("ratings").child("rateCount").setValue("0");
                ref.child(name).child("ratings").child("totalRate").setValue("0");
                ref.child(name).child("name").setValue(eventName);
                ref.child("eventCount").setValue(Integer.toString(c));
                ref.child(name).child("comments")
                        .child("commentCount").setValue("0");
            }
        });
    }
}