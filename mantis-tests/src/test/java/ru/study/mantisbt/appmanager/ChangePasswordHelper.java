package ru.study.mantisbt.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.study.mantisbt.model.MailMessage;
import ru.study.mantisbt.model.UserData;

import java.util.List;

/**
 * Created by Dreamer on 20.12.2016.
 */
public class ChangePasswordHelper extends HelperBase {
    private WebDriver wd;

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public String make(UserData user) throws InterruptedException {
        login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        reset(user);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        return change(app.mail().findConfirmationLink(mailMessages, user.getEmail()));
    }

    private String change(String confirmationLink) {
        wd.get(confirmationLink);
        String password = ""+System.currentTimeMillis();
        type(By.name("password"),password);
        type(By.name("password_confirm"),password);
        click(By.cssSelector("input[value='Изменить учетную запись']"));
        return password;
    }

    public void reset(UserData user) {
        click(By.linkText("управление"));
        click(By.linkText("Управление пользователями"));
        click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + user.getId() + "']"));
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    private void login(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.className("button"));
    }

}
