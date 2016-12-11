package ru.study.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.List;

/**
 * Created by Dreamer on 27.10.2016.
 */
public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void type(By locator, String text) {
        if (text != null) {
            WebElement element = findElement(locator);
            element.click();

            String existingText = element.getAttribute("value");
            if (!text.equals(existingText)) {
                element.clear();
                element.sendKeys(text);
            }
        }
    }

    protected void attach(By locator, File file) {
        if (file != null) {
            findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    protected void click(By locator) {
        findElement(locator).click();
    }

    protected void click(By locator, int index) {
        findElements(locator).get(index).click();
    }


    protected WebElement findElement(By locator) {
        return wd.findElement(locator);
    }

    protected List<WebElement> findElements(By locator) {
        return wd.findElements(locator);
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

    protected boolean isElementPresent(By locator) {
        try {
            findElement(locator);
            return true;
        }catch(NoSuchElementException ex) {
            return false;
        }
    }
}
