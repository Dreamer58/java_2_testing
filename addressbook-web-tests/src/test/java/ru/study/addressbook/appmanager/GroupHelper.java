package ru.study.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.study.addressbook.model.GroupData;
import ru.study.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dreamer on 27.10.2016.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
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

    public void deleteGroup() {
        click(By.name("delete"));
    }

    public void selectGroupById(int id) throws InterruptedException {
        click(By.cssSelector("input[value='" + id + "']"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCash = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) throws InterruptedException {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCash = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) throws InterruptedException {
        selectGroupById(group.getId());
        deleteGroup();
        groupCash = null;
        returnToGroupPage();
    }

    public int count() {
        return findElements(By.name("selected[]")).size();
    }

    private Groups groupCash = null;

    public Groups all() {
        if (groupCash != null) {
            return new Groups(groupCash);
        }

        groupCash = new Groups();
        List<WebElement> elements = findElements(By.cssSelector("span.group"));

        for(WebElement element: elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCash.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCash);
    }

}
