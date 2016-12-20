package ru.study.mantisbt.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.study.mantisbt.model.UserData;
import ru.study.mantisbt.model.Users;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by Dreamer on 20.12.2016.
 */
public class ChangePasswordTests extends TestBase {
    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws InterruptedException, IOException {
        Users users = app.db().users();
        UserData user = users.iterator().next();
        if (user.getUsername() == "administrator") {
            user = users.iterator().next();
        }
        String password = app.changePassword().make(user);

        assertTrue(app.newSession().login(user.getUsername(), password));

    }
}
