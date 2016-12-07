package ru.study.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Dreamer on 27.10.2016.
 */
public class HelperBase {
    protected FirefoxDriver wd;

    public HelperBase(FirefoxDriver wd) {
        this.wd = wd;
    }

    protected void type(By locator, String text) {
        WebElement element = findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By locator) {
        findElement(locator).click();
    }

    protected WebElement findElement(By locator) {
        return wd.findElement(locator);
    }

    protected void alertAccept(){
        wd.switchTo().alert().accept();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
