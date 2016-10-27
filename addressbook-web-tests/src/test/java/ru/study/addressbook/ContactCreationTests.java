package ru.study.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void ContactCreationTests() {
        initialCreateContact();
        fillContactForm(new ContactData("first name", "middle name", "last name", "nickname", "title", "company", "1 1 1", "4950101010", "9273826001", "4950101010", "test@mail.ru", "test2@gmail.com"));
        submitCreateContact();
        returnToHomePage();
    }

}
