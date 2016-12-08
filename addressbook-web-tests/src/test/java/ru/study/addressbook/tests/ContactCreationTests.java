package ru.study.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.study.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() throws InterruptedException {
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("first name", "middle name", "last name", "nickname",
                "title", "company", "1 1 1", "4950101010", "9273826001", "4950101010", "test@mail.ru",
                "test2@gmail.com", "test10");
        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()+1);

        Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        contact.setId(after.stream().max(byId).get().getId());
        before.add(contact);
        before.sort(byId);
        before.sort(byId);
        Assert.assertEquals(before, after);
    }

}
