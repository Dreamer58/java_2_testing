package ru.study.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.study.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test(enabled = true)
    public void ContactCreationTests() throws InterruptedException {
        List<ContactData> before = app.contact().list();
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
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size()+1);

        Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        contact.withId(after.stream().max(byId).get().getId());
        before.add(contact);
        before.sort(byId);
        before.sort(byId);
        Assert.assertEquals(before, after);
    }

}
