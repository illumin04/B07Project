package com.example.login_backend;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login_student_model {
    FirebaseDatabase db;

    FirebaseAuth mAuth;

    public Login_student_model(){
        db = FirebaseDatabase.getInstance();
        mAuth= FirebaseAuth.getInstance();
    }

    protected void attemptLogIn(String username, String email, String password,
                                Login_student_presenter presenter){
        // passing data to RTD
        DatabaseReference ref =db.getReference("Users")
                .child(username);

        ref.child("username").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String userName = task.getResult().getValue(String.class);
                if(userName==null||userName.equals("")){
                    noUserException(presenter);
                }else{
                    DatabaseReference reference = ref.child("usertype");
                    reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            boolean adminb = task.getResult().getValue(Boolean.class);
                            logIn(adminb, email, password, presenter);
                        }
                    });
                }
            }
        });
    }


    protected void logIn(boolean adminb,
                         String email, String password, Login_student_presenter presenter){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        presenter.presentProgressGone();
                        if (task.isSuccessful() && !adminb) {
                            presenter.presentSuccessfulStudent();
                        } else if (task.isSuccessful() && adminb) {
                            presenter.presentSuccessfulAdmin();
                        } else {
                            presenter.presentShowAuthFail();
                        }
                    }
                });
    }

    protected void noUserException(Login_student_presenter presenter){
        presenter.presentNoUser();
    }
}
