package com.example.login_backend;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login_student_model {
    FirebaseDatabase db;

    public Login_student_model(){
        db = FirebaseDatabase.getInstance();
    }

    protected void attemptLogIn(String username, String email, String password,
                                Login_student_presenter presenter){
        // passing data to RTD
        DatabaseReference reference =db.getReference("Users");
        reference=reference.child(username).child("usertype");

        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.getResult().getValue() == null){
                    noUserException(presenter);
                }else{
                    boolean adminb = task.getResult().getValue(Boolean.class);
                    presenter.logIn(adminb, email, password);
                }
            }
        });
    }

    protected void noUserException(Login_student_presenter presenter){
        presenter.presentNoUser();
    }
}
