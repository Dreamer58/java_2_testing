package ru.study.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.study.addressbook.model.ContactData;
import ru.study.addressbook.model.GroupData;

/**
 * Created by Dreamer on 07.12.2016.
 */
public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion() throws InterruptedException {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("first name", "middle name", "last name", "nickname",
                    "title", "company", "1 1 1", "4950101010", "9273826001", "4950101010", "test@mail.ru",
                    "test2@gmail.com", "test10"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before - 1);
        app.getContactHelper().submitDeleteContact();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before-1);
    }
}
