package com.example.login_backend;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Event {
    String name;
    String details;
    int limit;

    public Event(int eventId){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().
                child("Events").child("event" + eventId);
        ref.child("name").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String eventName = task.getResult().getValue(String.class);
                setName(eventName);
            }
        });
        ref.child("details")
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        String eventDetails = task.getResult().getValue(String.class);
                        setDetails(eventDetails);
                    }
                });
        ref.child("limit").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                int eventLimit = Integer.parseInt(task.getResult().getValue(String.class));
                setLimit(eventLimit);
            }
        });
    }


    private void setName(String name){
        this.name = name;
    }


    private void setDetails(String details){
        this.details = details;
    }


    private void setLimit(int limit){
        this.limit = limit;
    }

    public void show(){
        Log.i("name", name);
        Log.i("details", details);
        Log.i("limit", Integer.toString(limit));
    }
}
