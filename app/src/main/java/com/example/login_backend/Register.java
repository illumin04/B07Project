package com.example.login_backend;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.login_backend.databinding.ActivityMainBinding;
import com.example.login_backend.databinding.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    //ActivityMainBinding binding;
    DatabaseReference reference;
    FirebaseDatabase db;
    String email, password,username;// record for database
    boolean admin=false;//record for database if admin or not.
    TextInputEditText editTextEmail, editTextPassword,editTextUsername;
    Button buttonReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
    CheckBox checkBox;
    /*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent (getApplicationContext(), MainActivity.class);
            // modify this to connect to student main activity
            startActivity(intent);
            finish();
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_register);
        mAuth= FirebaseAuth.getInstance();
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        editTextUsername=findViewById(R.id.username);
        buttonReg = findViewById(R.id.btn_register);
        progressBar=findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginnow);
        checkBox=findViewById(R.id.checkbox);
        admin= checkBox.isChecked();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Login_student.class);
                startActivity(intent);
                finish();
            }
        });
        //admin box check
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                admin=checkBox.isChecked();
            }
        });
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                //String email, password;
                username= String.valueOf(editTextUsername.getText());
                email= String.valueOf(editTextEmail.getText());
                password=String.valueOf(editTextPassword.getText());
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(Register.this,"Enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this,"Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                // adding to RTD
                if(!email.isEmpty() && !password.isEmpty() && !username.isEmpty())
                {
                    //Log.d("tag","Entering a loop");
                    admin=checkBox.isChecked();
                    Users users= new Users(username,password,admin);
                    db = FirebaseDatabase.getInstance();
                    reference=db.getReference("Users");
                    reference.child(username).setValue(users).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
                //creating account using firebase auth
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful() && !admin) {
                                    Toast.makeText(Register.this, "Student account created.",
                                            Toast.LENGTH_SHORT).show();


                                } else if (task.isSuccessful() && admin) {
                                    Toast.makeText(Register.this, "Admin account created.",
                                            Toast.LENGTH_SHORT).show();


                                }
                                else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }
        });

    }
}