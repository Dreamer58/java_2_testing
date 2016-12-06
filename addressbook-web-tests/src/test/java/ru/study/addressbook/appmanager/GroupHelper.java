package ru.study.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.study.addressbook.model.GroupData;

/**
 * Created by Dreamer on 27.10.2016.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void initGroupCreation() {
        findElement(By.name("new")).click();
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void submitGroupCreation() {
        findElement(By.name("submit")).click();
    }

    public void returnToGroupPage() {
        findElement(By.linkText("group page")).click();
    }

    public void deleteSelectedGroup() {
        findElement(By.name("delete")).click();
    }

    public void selectGroup() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("selected[]")));
        element.click();
    }
}
