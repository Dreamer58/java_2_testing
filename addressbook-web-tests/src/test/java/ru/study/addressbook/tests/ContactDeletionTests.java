package ru.study.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.study.addressbook.model.ContactData;
import ru.study.addressbook.model.GroupData;

import java.util.List;

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
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().submitDeleteContact();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(before.size()-1);
        Assert.assertEquals(before, after);
    }
}
