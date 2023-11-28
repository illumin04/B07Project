package com.example.login_backend;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginUnitTest {

    @Mock
    Login_student mockLoginStudent;

    //@Mock
    //EditText editText;

    //@Mock
    //Editable edit;

    //@Mock
    //View baseView;

    @Test
    public void exampleTest(){
        assertEquals(2 + 2, 4);
    }

    /* copy pasted from example
    @Test
    public void checkEmptyUsername(){
        MainActivityPresenter presenter = new MainActivityPresenter(view, model);
        presenter.checkDB("");
        verify(view).setOutputText("String cannot be empty!");
    }

    @Test
    public void checkDBClick(){
        when(editText.getText()).thenReturn(edit);
        when(edit.toString()).thenReturn("Test");
    }
     */
}