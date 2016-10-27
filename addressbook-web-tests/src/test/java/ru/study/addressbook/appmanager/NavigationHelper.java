package ru.study.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Dreamer on 27.10.2016.
 */
public class NavigationHelper extends HelperBase{

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void gotoGroupsPage() throws InterruptedException {
        //прочитал, что способ самый плохой, но остальные найденные не помогли
        //без задержки тесты падают, так как клик по ссылке не успевает отработать
        //а отрабатывают следующие методы
        Thread.sleep(3000);
        click(By.linkText("groups"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

}
