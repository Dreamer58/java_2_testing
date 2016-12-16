package ru.study.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.study.addressbook.model.ContactData;
import ru.study.addressbook.model.Contacts;
import ru.study.addressbook.model.GroupData;
import ru.study.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Dreamer on 16.12.2016.
 */
public class ContactAddToGroupTests extends TestBase {
    private int contactId;
    private int groupId;

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        // если нет контактов, то добавляем контакт, не привязанный ни к какой группе
        if (app.db().contacts().size() == 0) {
            app.goTo().homePageFromAnotherPage();
            ContactData contactNew = new ContactData()
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
                    .withEmail3("test3@list.ru");
            app.contact().create(contactNew);
        }
        // если нет групп то добавляем группу и у нас уже имеется как минимум 1 контакт без привязки к группе,
        // поэтому выбираем любой контакт и выходим из метода
        if (app.db().groups().size() == 0 ) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test10"));
            groupId = app.db().groups().iterator().next().getId();
            contactId = app.db().contacts().iterator().next().getId();
            return;
        }
        // проверяем контакты на возможность включения в какую-либо группу
        // если находим такой, то выбираем его и выходим из метода
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() != groups.size()) {
                for (GroupData group : groups) {
                    if (!contact.getGroups().contains(group)) {
                        contactId = contact.getId();
                        groupId = group.getId();
                        return;
                    }
                }
            }
        }
        // если контакт, который можно привязать к какой-либо группе не найден,
        // то создаем новую группу и выбираем любой контакт
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test11"));
        groupId = app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt();

        contactId = app.db().contacts().iterator().next().getId();
    }

    @Test
    public void testContactAddToGroup() {
        app.goTo().homePageFromAnotherPage();
        Contacts before = app.db().contacts();
        app.contact().addToGroup(contactId, groupId);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));
    }

}
