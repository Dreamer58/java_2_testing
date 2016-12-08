package ru.study.addressbook.tests;

import org.testng.annotations.Test;
import ru.study.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void ContactCreationTests() throws InterruptedException {
        app.getContactHelper().createContact(new ContactData("first name", "middle name", "last name", "nickname",
                "title", "company", "1 1 1", "4950101010", "9273826001", "4950101010", "test@mail.ru",
                "test2@gmail.com", "test10"));
        app.getNavigationHelper().returnToHomePage();
    }

}
