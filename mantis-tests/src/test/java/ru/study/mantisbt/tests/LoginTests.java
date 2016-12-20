package ru.study.mantisbt.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.study.mantisbt.appmanager.HttpSession;

import java.io.IOException;

/**
 * Created by Dreamer on 17.12.2016.
 */
public class LoginTests extends TestBase {
    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        Assert.assertTrue(session.login("administrator", "root"));
        Assert.assertTrue(session.isLoggedInAs("administrator"));

    }
}
