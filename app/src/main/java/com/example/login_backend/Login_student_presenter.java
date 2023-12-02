package com.example.login_backend;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_student_presenter {
    Login_student_view view;
    Login_student_model model;
    FirebaseAuth mAuth;

    public Login_student_presenter(Login_student_view view, Login_student_model model){
        this.view = view;
        this.model = model;
        this.mAuth= FirebaseAuth.getInstance();
    }

    protected void logIn(boolean adminb, String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        view.setProgressGone();
                        if (task.isSuccessful() && !adminb) {
                            view.successfulStudent();
                        } else if (task.isSuccessful() && adminb) {
                            view.successfulAdmin();
                        } else {
                            view.showAuthFail();
                        }
                    }
                });
    }

    protected void checkLogIn(){
        String username = view.getUsername();
        String email = view.getEmail();
        String password = view.getPassword();
        if(TextUtils.isEmpty(email)){
            view.showNoEmail();
        }else if(TextUtils.isEmpty(password)) {
            view.showNoPassword();
        }else{
            model.attemptLogIn(username, email, password, Login_student_presenter.this);
        }
    }

    protected void presentNoUser(){
        view.showNoUser();
    }
}
