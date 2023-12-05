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

    public Login_student_presenter(Login_student_view view, Login_student_model model){
        this.view = view;
        this.model = model;
    }



    protected void checkLogIn(){
        String username = view.getUsername();
        String email = view.getEmail();
        String password = view.getPassword();
        if(email.equals("")){
            view.showNoEmail();
        }else if(password.equals("")) {
            view.showNoPassword();
        }else if(username.equals("")){
            view.showNoUser();
        }else{
            model.attemptLogIn(username, email, password, Login_student_presenter.this);
        }
    }

    protected void presentNoUser(){
        view.showNoUser();
    }

    protected void presentProgressGone(){
        view.setProgressGone();
    }

    protected void presentSuccessfulStudent(){
        view.successfulStudent();
    }

    protected void presentSuccessfulAdmin(){
        view.successfulAdmin();
    }

    protected void presentShowAuthFail(){
        view.showAuthFail();
    }
}
