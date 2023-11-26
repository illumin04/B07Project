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

public class NewComplaint extends AppCompatActivity {

    EditText complaintMessageEditText;
    FirebaseDatabase db;

    private void getComplainText() {
        complaintMessageEditText = findViewById(R.id.details);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_complaint);
        getComplainText();
        Button backButton = findViewById(R.id.backButton);
        Button submitButton = findViewById(R.id.submitButton);

        db = FirebaseDatabase.getInstance();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the current activity and go back
                GoBack();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitComplaint();
                Toast.makeText(NewComplaint.this, "Successfully submitted complaint", Toast.LENGTH_SHORT).show();
                GoBack();
            }
        });
    }

    public void GoBack() {
        finish();
    }

    public void submitComplaint() {
        // Get the complaint message and handle submission logic
        String complaintMessage = complaintMessageEditText.getText().toString();
        // Submit to the database
        submit(complaintMessage);

    }

    private void submit(String complaint) {
        DatabaseReference ref = db.getReference();
        DatabaseReference query = ref.child("complaints");
        DatabaseReference count = query.child("complaintCount");
        count.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String complaintCountstring = task.getResult().getValue().toString();
                int complaintCount = Integer.parseInt(complaintCountstring);
                //add the complaint count by 1
                complaintCount += 1;
                String ComplaintName = "complaint" + complaintCount;
                complaintCountstring = Integer.toString(complaintCount);
//                setOutputText(ComplaintName);
                //Submit the complaint to firebase
                query.child("complaintCount").setValue(complaintCountstring);
                query.child(ComplaintName).setValue(complaint);
            }
        });

    }

//    private void setOutputText(String s) {
//        TextView output = (TextView) findViewById(R.id.output);
//        output.setText(s);
//    }
}