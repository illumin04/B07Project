package com.example.login_backend;

public class StudentLoginPresenter {
    private StudentLoginModel model;
    private Login_student view; // Using Login_student as the View

    public StudentLoginPresenter(Login_student view) {
        this.model = new StudentLoginModel();
        this.view = view;
    }

    public void attemptLogin(String email, String password, String username) {
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
}
