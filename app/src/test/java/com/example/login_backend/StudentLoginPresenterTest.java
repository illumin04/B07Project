package com.example.login_backend;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentLoginPresenterTest {

    @Mock
    private Login_student mockView;

    @Mock
    private StudentLoginModel mockModel;

    private StudentLoginPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new StudentLoginPresenter(mockView, mockModel);
    }

    @Test
    public void attemptLogin_withValidCredentials_shouldNavigateToDestinationActivity() {
        // Given
        String email = "233@qq.com";
        String password = "qazxsw";
        String username = "kev";

        // Mock successful authentication
        doAnswer(invocation -> {
            StudentLoginModel.OnLoginListener listener = invocation.getArgument(3);
            listener.onLoginSuccess(StudentHomePage.class, username);
            return null;
        }).when(mockModel).authenticateUser(any(), any(), any(), any());

        // When
        presenter.attemptLogin(email, password, username);

        // Then
        verify(mockView).hideLoading();
        verify(mockView).navigateTo(StudentHomePage.class, username);
    }

    @Test
    public void attemptLogin_withInvalidCredentials_shouldShowLoginError() {
        // Given
        String email = "invalid@example.com";
        String password = "wrongPassword";
        String username = "invalidUser";

        // Mock authentication failure
        doAnswer(invocation -> {
            StudentLoginModel.OnLoginListener listener = invocation.getArgument(3);
            listener.onLoginFailure("Authentication failed.");
            return null;
        }).when(mockModel).authenticateUser(any(), any(), any(), any());

        // When
        presenter.attemptLogin(email, password, username);

        // Then
        verify(mockView).hideLoading();
        verify(mockView).showLoginError("Authentication failed.");
    }

    @Test
    public void attemptLogin_withEmptyEmail_shouldShowLoginError() {
        // Given
        String email = "";
        String password = "qazxsw";
        String username = "kev";

        // When
        presenter.attemptLogin(email, password, username);

        // Then
        verify(mockView).showLoginError("Enter email");
        verify(mockView, never()).navigateTo(any(), any()); // Ensure navigateTo is never called
    }

    @Test
    public void attemptLogin_withEmptyPassword_shouldShowLoginError() {
        // Given
        String email = "233@qq.com";
        String password = "";
        String username = "kev";

        // When
        presenter.attemptLogin(email, password, username);

        // Then
        verify(mockView).showLoginError("Enter password");
        verify(mockView, never()).navigateTo(any(), any()); // Ensure navigateTo is never called
    }
}

