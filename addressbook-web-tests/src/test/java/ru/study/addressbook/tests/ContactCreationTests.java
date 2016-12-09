package ru.study.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.study.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test(enabled = true)
    public void ContactCreationTests() throws InterruptedException {
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("first name")
                .withMiddlename("middle name")
                .withLastname("last name")
                .withNickname("nickname")
                .withTitle("title")
                .withCompany("company")
                .withAddress("1 1 1")
                .withHomePhone("4950101010")
                .withMobilePhone("9273826001")
                .withWorkPhone("4950101010")
                .withEmail1("test@mail.ru")
                .withEmail2("test2@gmail.com")
                .withGroup("test10");
        app.contact().create(contact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size()+1);

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}
