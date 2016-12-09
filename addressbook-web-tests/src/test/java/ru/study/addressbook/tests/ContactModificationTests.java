package ru.study.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.study.addressbook.model.ContactData;
import ru.study.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Dreamer on 07.12.2016.
 */
public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        app.goTo().homePageFromAnotherPage();
        if (app.contact().all().size() == 0) {
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
        }
    }

    @Test(enabled = true)
    public void testContactModification() throws InterruptedException {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("first name")
                .withMiddlename("middle2 name")
                .withLastname("last name")
                .withNickname("nickname5")
                .withTitle("title1")
                .withCompany("company5")
                .withAddress("1 1 12")
                .withHomePhone("4950101012")
                .withMobilePhone("9273826002")
                .withWorkPhone("4950101012")
                .withEmail1("test_1@mail.ru")
                .withEmail2("test2_1@gmail.com");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
