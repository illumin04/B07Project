//package com.example.login_backend;
//
//import android.content.Context;
//
//import androidx.test.platform.app.InstrumentationRegistry;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import static org.junit.Assert.*;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import org.junit.Before;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import static org.mockito.Mockito.*;
//
///**
// * Instrumented test, which will execute on an Android device.
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
//@RunWith(MockitoJUnitRunner.class)
//public class ExampleInstrumentedTest {
//
//    @Mock
//    private Login_student_view mockView;
//
//    @Mock
//    private Login_student_model mockModel;
//
//    @Mock
//    FirebaseAuth mockFirebaseAuth;
//
//    @InjectMocks
//    Login_student_presenter presenter;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        presenter.mAuth = mockFirebaseAuth;
//    }
//
//    @Test
//    public void testLogIn_SuccessfulStudent() {
//        // Arrange
//        when(mockView.getUsername()).thenReturn("kev");
//        when(mockView.getEmail()).thenReturn("233@qq.com");
//        when(mockView.getPassword()).thenReturn("qazxsw");
//
//        // Simulate that the user exists in the Realtime Database as a student
//        doAnswer(invocation -> {
//            Login_student_presenter presenterCallback = invocation.getArgument(3);
//            presenterCallback.logIn(false, "233@qq.com", "qazxsw");
//            return null;
//        }).when(mockModel).attemptLogIn(Mockito.eq("kev"), Mockito.anyString(), Mockito.anyString(), Mockito.any());
//
//        // Simulate a successful authentication
//        when(mockFirebaseAuth.signInWithEmailAndPassword("233@qq.com", "qazxsw"))
//                .thenReturn(Mockito.mock(Task.class));
//
//        // Act
//        presenter.checkLogIn();
//
//        // Assert
//        verify(mockView).setProgressVisible();
//        verify(mockView).setProgressGone();
//        verify(mockView).successfulStudent();
//        verify(mockFirebaseAuth).signInWithEmailAndPassword("233@qq.com", "qazxsw");
//    }
//
//    @Test
//    public void testLogIn_SuccessfulAdmin() {
//        // Arrange
//        when(mockView.getUsername()).thenReturn("kevs");
//        when(mockView.getEmail()).thenReturn("333@qq.com");
//        when(mockView.getPassword()).thenReturn("qazxsw");
//
//        // Simulate that the user exists in the Realtime Database as an admin
//        doAnswer(invocation -> {
//            Login_student_presenter presenterCallback = invocation.getArgument(3);
//            presenterCallback.logIn(true, "333@qq.com", "qazxsw");
//            return null;
//        }).when(mockModel).attemptLogIn(Mockito.eq("kevs"), Mockito.anyString(), Mockito.anyString(), Mockito.any());
//
//        // Simulate a successful authentication
//        when(mockFirebaseAuth.signInWithEmailAndPassword("333@qq.com", "qazxsw"))
//                .thenReturn(Mockito.mock(Task.class));
//
//        // Act
//        presenter.checkLogIn();
//
//        // Assert
//        verify(mockView).setProgressVisible();
//        verify(mockView).setProgressGone();
//        verify(mockView).successfulAdmin();
//        verify(mockFirebaseAuth).signInWithEmailAndPassword("333@qq.com", "qazxsw");
//    }
//
//    @Test
//    public void testLogIn_FailedLogin() {
//        // Arrange
//        when(mockView.getUsername()).thenReturn("someUser");
//        when(mockView.getEmail()).thenReturn("invalid@email.com");
//        when(mockView.getPassword()).thenReturn("invalidPassword");
//
//        // Simulate that the user does not exist in the Realtime Database
//        doAnswer(invocation -> {
//            Login_student_presenter presenterCallback = invocation.getArgument(3);
//            presenterCallback.presentNoUser();
//            return null;
//        }).when(mockModel).attemptLogIn(Mockito.eq("someUser"), Mockito.anyString(), Mockito.anyString(), Mockito.any());
//
//        // Simulate an unsuccessful authentication
//        when(mockFirebaseAuth.signInWithEmailAndPassword("invalid@email.com", "invalidPassword"))
//                .thenReturn(Mockito.mock(Task.class));
//
//        // Act
//        presenter.checkLogIn();
//
//        // Assert
//        verify(mockView).setProgressVisible();
//        verify(mockView).setProgressGone();
//        verify(mockView).showNoUser();
//    }
//
//    @Test
//    public void testLogIn_EmptyUsername() {
//        // Arrange
//        when(mockView.getUsername()).thenReturn("");
//        when(mockView.getEmail()).thenReturn("233@qq.com");
//        when(mockView.getPassword()).thenReturn("qazxsw");
//
//        // Act
//        presenter.checkLogIn();
//
//        // Assert
//        verify(mockView).showNoUser();
//    }
//
//    @Test
//    public void testLogIn_EmptyEmail() {
//        // Arrange
//        when(mockView.getUsername()).thenReturn("kev");
//        when(mockView.getEmail()).thenReturn("");
//        when(mockView.getPassword()).thenReturn("qazxsw");
//
//        // Act
//        presenter.checkLogIn();
//
//        // Assert
//        verify(mockView).showNoEmail();
//    }
//
//    @Test
//    public void testLogIn_EmptyPassword() {
//        // Arrange
//        when(mockView.getUsername()).thenReturn("kev");
//        when(mockView.getEmail()).thenReturn("233@qq.com");
//        when(mockView.getPassword()).thenReturn("");
//
//        // Act
//        presenter.checkLogIn();
//
//        // Assert
//        verify(mockView).showNoPassword();
//    }
//}