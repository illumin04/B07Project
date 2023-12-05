package com.example.login_backend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login_student_view extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword, editusername;
    Button buttonLog;
    ProgressBar progressBar;
    TextView textView;
    Login_student_presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);
        init();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Login_student_view.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProgressVisible();
                presenter.checkLogIn();
            }
        });
    }

    protected void init(){
        presenter = new Login_student_presenter(Login_student_view.this,
                new Login_student_model());
        editusername=findViewById(R.id.username);
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        buttonLog = findViewById(R.id.btnlogin);
        progressBar=findViewById(R.id.progressBar);
        textView = findViewById(R.id.regesternow);
    }

    protected void showNoEmail(){
        Toast.makeText(Login_student_view.this,"Enter email", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.INVISIBLE);
    }

    protected void showNoPassword(){
        Toast.makeText(Login_student_view.this, "Enter password", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.INVISIBLE);
    }

    protected void showNoUser(){
        Toast.makeText(Login_student_view.this,
                "Username does not exist!", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.INVISIBLE);
    }

    protected String getUsername(){
        return String.valueOf(editusername.getText());
    }

    protected String getEmail(){
        return String.valueOf(editTextEmail.getText());
    }

    protected String getPassword(){
        return String.valueOf(editTextPassword.getText());
    }

    protected void setProgressGone(){
        progressBar.setVisibility(View.GONE);
    }

    protected void setProgressVisible(){
        progressBar.setVisibility(View.VISIBLE);
    }

    protected void successfulStudent(){
        // Sign in success, update UI with the signed-in user's information
        Toast.makeText(Login_student_view.this,"Student login successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent (Login_student_view.this, StudentHomePage.class);
        // modify this to connect to student main activity
        intent.putExtra("username", editusername.getText().toString());
        startActivity(intent);
    }

    protected void successfulAdmin(){
        // Sign in success, update UI with the signed-in user's information
        Toast.makeText(Login_student_view.this,"Admin login successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent (Login_student_view.this,Admin_main.class);
        // modify this to connect to student main activity
        intent.putExtra("username", editusername.getText().toString());
        startActivity(intent);
    }

    protected void showAuthFail(){
        // If sign in fails, display a message to the user.
        Toast.makeText(Login_student_view.this, "Authentication failed.",
                Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.INVISIBLE);
    }
}