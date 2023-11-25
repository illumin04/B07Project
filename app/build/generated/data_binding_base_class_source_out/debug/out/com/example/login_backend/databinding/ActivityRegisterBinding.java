// Generated by view binder compiler. Do not edit!
package com.example.login_backend.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.login_backend.R;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnRegister;

  @NonNull
  public final CheckBox checkbox;

  @NonNull
  public final TextInputEditText email;

  @NonNull
  public final TextView loginnow;

  @NonNull
  public final TextInputEditText password;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final TextView signin;

  @NonNull
  public final TextInputEditText username;

  private ActivityRegisterBinding(@NonNull LinearLayout rootView, @NonNull Button btnRegister,
      @NonNull CheckBox checkbox, @NonNull TextInputEditText email, @NonNull TextView loginnow,
      @NonNull TextInputEditText password, @NonNull ProgressBar progressBar,
      @NonNull TextView signin, @NonNull TextInputEditText username) {
    this.rootView = rootView;
    this.btnRegister = btnRegister;
    this.checkbox = checkbox;
    this.email = email;
    this.loginnow = loginnow;
    this.password = password;
    this.progressBar = progressBar;
    this.signin = signin;
    this.username = username;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_register;
      Button btnRegister = ViewBindings.findChildViewById(rootView, id);
      if (btnRegister == null) {
        break missingId;
      }

      id = R.id.checkbox;
      CheckBox checkbox = ViewBindings.findChildViewById(rootView, id);
      if (checkbox == null) {
        break missingId;
      }

      id = R.id.email;
      TextInputEditText email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.loginnow;
      TextView loginnow = ViewBindings.findChildViewById(rootView, id);
      if (loginnow == null) {
        break missingId;
      }

      id = R.id.password;
      TextInputEditText password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.signin;
      TextView signin = ViewBindings.findChildViewById(rootView, id);
      if (signin == null) {
        break missingId;
      }

      id = R.id.username;
      TextInputEditText username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((LinearLayout) rootView, btnRegister, checkbox, email,
          loginnow, password, progressBar, signin, username);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}