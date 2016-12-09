package ru.study.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.study.addressbook.model.ContactData;
import ru.study.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dreamer on 27.10.2016.
 */
public class ContactHelper extends HelperBase{
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initialCreateContact() throws InterruptedException {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());

        if (creation){
            new Select(findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitCreateContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void selectContactById(int id) {
        click(By.cssSelector("input[value='" + id + "']"));
    }

    public void submitDeleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        alertAccept();
    }

    public void initialModifyContact() {
        click(By.xpath("//tr[@class='odd']/td[8]/a/img"));
    }

    public void submitModifyContact() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contact) throws InterruptedException {
        initialCreateContact();
        fillContactForm(contact, true);
        submitCreateContact();
        contactCash = null;
    }

    public void modifyContact(ContactData contact) {
        initialModifyContact();
        fillContactForm(contact, false);
        submitModifyContact();
        contactCash = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        submitDeleteContact();
        contactCash = null;
    }

    public int getContactCount() {
        return findElements(By.name("selected[]")).size();
    }

    Contacts contactCash = null;

    public Contacts all() {
        if (contactCash != null) {
            return new Contacts(contactCash);
        }

        contactCash = new Contacts();
        List<WebElement> elements = findElements(By.name("entry"));

        for (WebElement element: elements){
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElement(By.xpath("//td[2]")).getText();
            String firstName = element.findElement(By.xpath("//td[3]")).getText();
            contactCash.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
        }

        return new Contacts(contactCash);
    }

}
