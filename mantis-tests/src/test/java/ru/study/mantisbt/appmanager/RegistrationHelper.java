package ru.study.mantisbt.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.study.mantisbt.model.MailMessage;

import java.util.List;

/**
 * Created by Dreamer on 19.12.2016.
 */
public class RegistrationHelper extends HelperBase{
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    private void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl")+"/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Зарегистрироваться']"));
    }

    private void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"),password);
        type(By.name("password_confirm"),password);
        click(By.cssSelector("input[value='Изменить учетную запись']"));
    }

    public void make(String username, String password, String email) throws InterruptedException {
        app.registration().start(username, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        app.registration().finish(app.mail().findConfirmationLink(mailMessages, email), password);

    }

}
