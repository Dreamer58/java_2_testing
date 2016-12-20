package ru.study.mantisbt.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dreamer on 27.10.2016.
 */
public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;

    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftpHelper;
    private MailHelper mailHelper;
    private ChangePasswordHelper changePasswordHelper;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void openURL(WebDriver wd, String url) {
        wd.get(url);
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        dbHelper = new DbHelper();
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public ChangePasswordHelper changePassword() {
        if (changePasswordHelper == null) {
            changePasswordHelper = new ChangePasswordHelper(this);
        }
        return changePasswordHelper;
    }

    public FtpHelper ftp() {
        if (ftpHelper == null) {
            ftpHelper = new FtpHelper(this);
        }
        return ftpHelper;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (Objects.equals(browser, BrowserType.FIREFOX)){
                wd = new FirefoxDriver();
            } else if (Objects.equals(browser, BrowserType.CHROME)){
                wd = new ChromeDriver();
            } else if (Objects.equals(browser, BrowserType.IE)){
                wd = new InternetExplorerDriver();
            }

            wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            openURL(wd, properties.getProperty("web.baseUrl"));
        }
        return wd;
    }
}
