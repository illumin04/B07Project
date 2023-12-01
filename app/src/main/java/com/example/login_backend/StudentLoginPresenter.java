package com.example.login_backend;


public class StudentLoginPresenter {
    private StudentLoginModel model;
    private Login_student view; // Using Login_student as the View

    public StudentLoginPresenter(Login_student view, StudentLoginModel model) {
        this.model = model;
        this.view = view;
    }

    public void attemptLogin(String email, String password, String username) {
        if (isEmpty(email)) {
            view.showLoginError("Enter email");
            return; // Return early if the email is empty
        }
        if (isEmpty(password)) {
            view.showLoginError("Enter password");
            return;
        }
        view.showLoading();

        model.authenticateUser(email, password, username, new StudentLoginModel.OnLoginListener() {
            @Override
            public void onLoginSuccess(Class<?> destinationActivity, String username) {
                view.hideLoading();
                view.navigateTo(destinationActivity, username);
            }

            @Override
            public void onLoginFailure(String errorMessage) {
                view.hideLoading();
                view.showLoginError(errorMessage);
            }
        });
    }

    public boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }
}
