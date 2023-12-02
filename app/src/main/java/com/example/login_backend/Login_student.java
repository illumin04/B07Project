package com.example.login_backend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_student extends AppCompatActivity {
    //ActivityMainBinding binding;
    DatabaseReference reference;
    FirebaseDatabase db;
    String username,email,password;
    TextInputEditText editTextEmail, editTextPassword,editusername;
    Button buttonLog;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);
        mAuth= FirebaseAuth.getInstance();
        editusername=findViewById(R.id.username);
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        buttonLog = findViewById(R.id.btnlogin);
        progressBar=findViewById(R.id.progressBar);
        textView = findViewById(R.id.regesternow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Login_student.this,Register.class);
                startActivity(intent);
                finish();
            }
        });
        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String email, password;
                username=String.valueOf(editusername.getText());
                email= String.valueOf(editTextEmail.getText());
                password=String.valueOf(editTextPassword.getText());
                //usertype=== (checkbox)
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login_student.this,"Enter email", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(Login_student.this, "Enter password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                // passing data to RTD
                db = FirebaseDatabase.getInstance();
                reference=db.getReference("Users");
                reference=reference.child(username).child("usertype");

                reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.getResult().getValue() == null){
                            Toast.makeText(Login_student.this,
                                    "Username does not exist!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }else{
                            boolean adminb = task.getResult().getValue(Boolean.class);
                            logIn(adminb);
                        }

                    }
                });

            }
        });
    }
    private void logIn(boolean adminb){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful() && !adminb) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login_student.this,"Student login successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (Login_student.this, StudentHomePage.class);
                            // modify this to connect to student main activity
                            intent.putExtra("username", editusername.getText().toString());
                            startActivity(intent);
                            finish();
                        } else if (task.isSuccessful() && adminb) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login_student.this,"Admin login successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (Login_student.this,Admin_main.class);
                            // modify this to connect to student main activity
                            intent.putExtra("username", editusername.getText().toString());
                            startActivity(intent);
                            finish();
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login_student.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }

    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }
    public void showLoginError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
    public void navigateTo(Class<?> destinationActivity, String username) {
        Intent intent = new Intent(Login_student.this, destinationActivity);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }
}