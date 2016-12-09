package ru.study.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.study.addressbook.model.ContactData;
import ru.study.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @Test(enabled = true)
    public void ContactCreationTests() throws InterruptedException {
        app.goTo().homePageFromAnotherPage();
        Contacts before = app.contact().all();
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
                .withEmail3("test3@list.ru")
                .withGroup("test10");
        app.contact().create(contact);
        app.goTo().homePageFromAlert();;
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
