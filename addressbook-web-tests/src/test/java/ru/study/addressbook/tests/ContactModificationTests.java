package ru.study.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.study.addressbook.model.ContactData;

/**
 * Created by Dreamer on 07.12.2016.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() throws InterruptedException {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("first name", "middle name", "last name", "nickname",
                    "title", "company", "1 1 1", "4950101010", "9273826001", "4950101010", "test@mail.ru",
                    "test2@gmail.com", "test10"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().initialModifyContact();
        app.getContactHelper().fillContactForm(new ContactData("first name1", "middle2 name", "last6 name", "nickname5",
                "title1", "company5", "1 1 12", "4950101012", "9273826002", "4950101012", "test_1@mail.ru",
                "test2_1@gmail.com", null), false);
        app.getContactHelper().submitModifyContact();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
