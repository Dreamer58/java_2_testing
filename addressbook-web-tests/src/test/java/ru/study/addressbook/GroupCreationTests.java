package ru.study.addressbook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

import static ru.study.addressbook.Helpers.*;

public class GroupCreationTests {
    FirefoxDriver wd;

    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        openURL(wd, "http://localhost/addressbook/group.php");
        login(wd, "admin", "secret");
    }


    @Test
    public void testGroupCreation() {
        gotoGroupsPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test1", "test2", "test3"));
        submitGroupCreation();
        returnToGroupPage();
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }



    private void gotoGroupsPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    private void initGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    private void fillGroupForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    private void submitGroupCreation() {
        wd.findElement(By.name("submit")).click();
    }

    private void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }
}
