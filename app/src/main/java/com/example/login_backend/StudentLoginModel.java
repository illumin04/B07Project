package com.example.login_backend;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentLoginModel {
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    private FirebaseDatabase db;
    private boolean admin;

    public StudentLoginModel() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Users");
    }

    public void authenticateUser(String email, String password, String username, final OnLoginListener listener) {
        // ... (same as before)
    }

    public interface OnLoginListener {
        void onLoginSuccess(Class<?> destinationActivity, String username);

        void onLoginFailure(String errorMessage);
    }
}

