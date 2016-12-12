package ru.study.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.study.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Dreamer on 09.12.2016.
 */
public class ContactInfoTests extends TestBase {
    private ContactData contact;

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (app.contact().all().size() == 0) {
            ContactData contact = new ContactData()
                    .withFirstname("first name")
                    .withMiddlename("middle name")
                    .withLastname("last name")
                    .withAddress("1 1 1")
                    .withHomePhone("4950101010")
                    .withMobilePhone("9273826001")
                    .withWorkPhone("4950101010")
                    .withEmail1("test@mail.ru")
                    .withEmail2("test2@gmail.com")
                    .withEmail3("test3@list.ru");
            app.contact().create(contact);
        }
        app.goTo().homePageFromAnotherPage();
        contact = app.contact().all().iterator().next();
    }

    @Test(enabled = true)
    public void testContactInfoOnMainPageWithEditPage() {
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(),equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(),equalTo(contactInfoFromEditForm.getAddress()));
    }

    @Test(enabled = false)
    public void testContactInfoOnMainPageWithDetailsPage() {
        ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);

        assertThat(contact.getFirstname() + " " + contact.getLastname(),
                equalTo(contactInfoFromDetailsForm.getLastandfirstname()));
        assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInfoFromDetailsForm)));
        assertThat(contact.getAllEmails(),equalTo(mergeEmails(contactInfoFromDetailsForm)));
        assertThat(contact.getAddress(),equalTo(contactInfoFromDetailsForm.getAddress()));

    }


    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactInfoTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()HMW:]", "");
    }

}
