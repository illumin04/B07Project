package com.example.login_backend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class NewEvent extends AppCompatActivity {

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        db = FirebaseDatabase.getInstance();
        EditText event_name = findViewById(R.id.name);
        event_name.setSelection(0);
        EditText event_details = findViewById(R.id.details);
        event_details.setSelection(0);
        EditText limit = findViewById(R.id.stu_limit);
        TextView eventMessage = findViewById(R.id.eventMessage);

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
                String time = getTime();
                if(time.equals("false")){
                    Toast.makeText(NewEvent.this, "Invalid date", Toast.LENGTH_SHORT).show();
                }else if(eventName.isEmpty()){
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
                String time = getTime();
                ref.child(name).child("details").setValue(details);
                ref.child(name).child("limit").setValue(limit);
                ref.child(name).child("rsvp").child("rsvpCount").setValue("0");
                ref.child(name).child("ratings").child("rateCount").setValue("0");
                ref.child(name).child("ratings").child("totalRate").setValue("0");
                ref.child(name).child("name").setValue(eventName);
                ref.child(name).child("time").setValue(time);
                ref.child("eventCount").setValue(Integer.toString(c));
                ref.child(name).child("comments")
                        .child("commentCount").setValue("0");
            }
        });
    }

    private String getTime(){
        EditText day = findViewById(R.id.day);
        EditText month = findViewById(R.id.month);
        EditText year = findViewById(R.id.year);
        String Day = day.getText().toString();
        String Month = month.getText().toString();
        String Year = year.getText().toString();
        if(!checkTime(Day, Month, Year)){
            return "false";
        }
        return Month + " / " + Day + " / " + Year;
    }

    private boolean checkTime(String day, String month, String year){
        int Day = Integer.parseInt(day);
        int Month = Integer.parseInt(month);
        int Year = Integer.parseInt(year);
        if (Month < 1 || Month > 12) {
            return false;
        }
        if(Year % 4 == 0){
            if(Year % 100 == 0){
                if(Year % 400 != 0){
                    return run(Day, Month);
                }
                return ping(Day, Month);
            }
            return run(Day, Month);
        }
        return ping(Day, Month);
    }


    private boolean run(int day, int month){
        int[] a = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return day > 0 && day <= a[month - 1];
    }

    private boolean ping(int day, int month){
        int[] a = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return day > 0 && day <= a[month - 1];
    }
}

