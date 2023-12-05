package com.example.login_backend;

import android.content.Context;

//import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    private Login_student_view mockView;

    @Mock
    private Login_student_model mockModel;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();


    @Test
    public void testLogIn_EmptyUsername() {
        // Arrange
        when(mockView.getUsername()).thenReturn("");
        when(mockView.getEmail()).thenReturn("233@qq.com");
        when(mockView.getPassword()).thenReturn("qazxsw");

        Login_student_presenter presenter = new Login_student_presenter(mockView, mockModel);

        // Act
        presenter.checkLogIn();

        // Assert
        verify(mockView).showNoUser();
    }

    @Test
    public void testLogIn_EmptyEmail() {
        // Arrange
        when(mockView.getUsername()).thenReturn("kev");
        when(mockView.getEmail()).thenReturn("");
        when(mockView.getPassword()).thenReturn("qazxsw");


        Login_student_presenter presenter = new Login_student_presenter(mockView, mockModel);

        // Act
        presenter.checkLogIn();

        // Assert
        verify(mockView).showNoEmail();
    }
//
    @Test
    public void testLogIn_EmptyPassword() {
        // Arrange
        when(mockView.getUsername()).thenReturn("kev");
        when(mockView.getEmail()).thenReturn("233@qq.com");
        when(mockView.getPassword()).thenReturn("");

        Login_student_presenter presenter = new Login_student_presenter(mockView, mockModel);

        // Act
        presenter.checkLogIn();

        // Assert
        verify(mockView).showNoPassword();
    }

    @Test
    public void testLogIn_Student(){
        when(mockView.getUsername()).thenReturn("andrew");
        when(mockView.getEmail()).thenReturn("123@mail.com");
        when(mockView.getPassword()).thenReturn("123456");


        Login_student_presenter presenter = new Login_student_presenter(mockView, mockModel);

        // Act
        presenter.checkLogIn();

        // Assert
        verify(mockModel).attemptLogIn("andrew",
                "123@mail.com", "123456", presenter);
    }

    @Test
    public void testLogIn_Admin(){
        when(mockView.getUsername()).thenReturn("admin");
        when(mockView.getEmail()).thenReturn("admin@mail.com");
        when(mockView.getPassword()).thenReturn("administrator");

        Login_student_presenter presenter = new Login_student_presenter(mockView,mockModel);

        presenter.checkLogIn();

        verify(mockModel).attemptLogIn("admin",
                "admin@mail.com", "administrator", presenter);
    }

    @Test
    public void testPresentNoUser(){
        Login_student_presenter presenter = new Login_student_presenter(mockView,mockModel);

        presenter.presentNoUser();

        verify(mockView).showNoUser();
    }

    @Test
    public void testPresentProgressGone(){
        Login_student_presenter presenter = new Login_student_presenter(mockView,mockModel);

        presenter.presentProgressGone();

        verify(mockView).setProgressGone();
    }

    @Test
    public void testSuccessfulStudent(){
        Login_student_presenter presenter = new Login_student_presenter(mockView,mockModel);

        presenter.presentSuccessfulStudent();

        verify(mockView).successfulStudent();
    }

    @Test
    public void testSuccessfulAdmin(){
        Login_student_presenter presenter = new Login_student_presenter(mockView,mockModel);

        presenter.presentSuccessfulAdmin();

        verify(mockView).successfulAdmin();
    }


    @Test
    public void testAuthFail(){
        Login_student_presenter presenter = new Login_student_presenter(mockView,mockModel);

        presenter.presentShowAuthFail();

        verify(mockView).showAuthFail();
    }


}