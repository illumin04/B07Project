package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class NewComplaintsActivity extends AppCompatActivity {
    EditText complaintMessageEditText;
    FirebaseDatabase db;

    private void getComplainText(){
        complaintMessageEditText = findViewById(R.id.complaintMessageEditText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_complaints);
        getComplainText();
        Button backButton = findViewById(R.id.backButtonNewComplaint);
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
            }
        });
    }
    public void GoBack(){
        finish();
    }
    public void submitComplaint(){
        // Get the complaint message and handle submission logic
        String complaintMessage = complaintMessageEditText.getText().toString();
        // Submit to the database
        submit(complaintMessage);

    }
    private void submit(String complaint){
        DatabaseReference ref = db.getReference();
        DatabaseReference query = ref.child("complaints");
        DatabaseReference count = query.child("complaintCount");
        count.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String complaintCountstring =  task.getResult().getValue().toString();
                int complaintCount = Integer.parseInt(complaintCountstring);
                //add the complaint count by 1
                complaintCount += 1;
                String ComplaintName =  "complaint" + complaintCount;
                complaintCountstring = Integer.toString(complaintCount);
                setOutputText(ComplaintName);
                //Submit the complaint to firebase
                query.child("complaintCount").setValue(complaintCountstring);
                query.child(ComplaintName).setValue(complaint);
            }
        });

    }

    private void setOutputText(String s)
    {
        TextView output = (TextView) findViewById(R.id.output);
        output.setText(s);
    }
}
