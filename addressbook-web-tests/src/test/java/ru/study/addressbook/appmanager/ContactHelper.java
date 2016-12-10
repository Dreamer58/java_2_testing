package ru.study.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.study.addressbook.model.ContactData;
import ru.study.addressbook.model.Contacts;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Dreamer on 27.10.2016.
 */
public class ContactHelper extends HelperBase {
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
        type(By.name("email3"), contactData.getEmail3());

        if (creation) {
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

    private void initContactModificationById(int id) {
        click(By.xpath("//tr[.//input[@value='" + id + "']]/td[8]/a"));
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

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitModifyContact();
        contactCash = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        submitDeleteContact();
        contactCash = null;
    }

    public int count() {
        return findElements(By.name("selected[]")).size();
    }

    Contacts contactCash = null;

    public Contacts all() {
        if (contactCash != null) {
            return new Contacts(contactCash);
        }

        contactCash = new Contacts();
        List<WebElement> rows = findElements(By.name("entry"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            String address = cells.get(3).getText();
            contactCash.add(new ContactData()
                    .withId(id)
                    .withFirstname(firstName)
                    .withLastname(lastName)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones)
                    .withAddress(address));
        }

        return new Contacts(contactCash);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        ContactData contactFromEditForm = new ContactData()
                .withFirstname(findElement(By.name("firstname")).getAttribute("value"))
                .withLastname(findElement(By.name("lastname")).getAttribute("value"))
                .withHomePhone(findElement(By.name("home")).getAttribute("value"))
                .withMobilePhone(findElement(By.name("mobile")).getAttribute("value"))
                .withWorkPhone(findElement(By.name("work")).getAttribute("value"))
                .withEmail1(findElement(By.name("email")).getAttribute("value"))
                .withEmail2(findElement(By.name("email2")).getAttribute("value"))
                .withEmail3(findElement(By.name("email3")).getAttribute("value"))
                .withAddress(findElement(By.name("address")).getAttribute("value"));

        wd.navigate().back();
        return contactFromEditForm;
    }

    public ContactData infoFromDetailsForm(ContactData contact) {
        viewDetailsContactById(contact.getId());
        WebElement details = findElement(By.id("content"));
        List<String> allDetails = Arrays.asList(details.getText().split("\n"));

        ContactData contactFromDetailsForm = new ContactData()
                .withFirstAndLastname(allDetails.get(0))
                .withAddress(allDetails.get(1))
                .withHomePhone(allDetails.get(3))
                .withMobilePhone(allDetails.get(4))
                .withWorkPhone(allDetails.get(5))
                .withEmail1(allDetails.get(7))
                .withEmail2(allDetails.get(8))
                .withEmail3(allDetails.get(9));

        wd.navigate().back();
        return contactFromDetailsForm;
    }

    private void viewDetailsContactById(int id) {
        click(By.xpath("//tr[.//input[@value='" + id + "']]/td[7]/a"));
    }
}
