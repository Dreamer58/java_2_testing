package ru.study.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.study.addressbook.model.GroupData;

/**
 * Created by Dreamer on 27.10.2016.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void deleteSelectedGroup() {
        click(By.name("delete"));
    }

    public void selectGroup() throws InterruptedException {
        //прочитал, что способ самый плохой, но остальные найденные не помогли
        //без задержки тест на удаление проходит успешно, но группа на самом деле не удаляется
        Thread.sleep(3000);
        click(By.name("selected[]"));
    }
}
