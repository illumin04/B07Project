package com.example.login_backend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewEvent extends AppCompatActivity {
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_event);
        db = FirebaseDatabase.getInstance();

        createLayout();

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void createLayout(){
        DatabaseReference ref = db.getReference().child("Events").child("eventCount");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                int count = Integer.parseInt(task.getResult().getValue().toString());
                for(int i=1;i<=count;++i){
                    addEventButton(i);
                }
            }
        });
    }
    private void addEventButton(int eventNum){
        LinearLayout ll = findViewById(R.id.linearLayout);
        DatabaseReference ref = db.getReference().child("Events")
                .child(getEventName(eventNum)).child("name");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String event = getEventName(eventNum);
                String eventName = task.getResult().getValue(String.class);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                );
                params.setMargins(10,15,10,15);
                Button btn = new Button(ViewEvent.this);
                btn.setText(eventName);
                btn.setId(eventNum);
                btn.setBackgroundColor(Color.rgb(70,80,90));
                ll.addView(btn, params);
                setButton(event, eventNum);
                Log.i(event, Integer.toString(eventNum));
            }
        });
    }


    private void setButton(String eventName, int id){
        Button button = findViewById(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewEvent.this,StudentEventHomePage.class);
                Bundle extras = getIntent().getExtras();
                String username = null;
                if (extras != null) {
                    username = extras.getString("username");
                }
                intent.putExtra("username", username);
                intent.putExtra("eventName", eventName);
                startActivity(intent);
            }
        });
    }


    private String getEventName(int num){
        return "event" + num;
    }
}