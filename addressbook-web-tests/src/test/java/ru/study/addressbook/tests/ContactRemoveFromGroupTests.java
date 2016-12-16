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
 * Created by Dreamer on 17.12.2016.
 */
public class ContactRemoveFromGroupTests extends TestBase {
    private int contactId;
    private int groupId;

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        // если нет контактов, то добавляем контакт, привязанный к группе
        // (ее либо создаем, либо выбираем любую из существующих) и выходим из метода
        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0 ) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test10"));
            }
            GroupData group = app.db().groups().iterator().next();
            groupId = group.getId();

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
                    .withEmail3("test3@list.ru")
                    .inGroup(group);

            app.contact().create(contactNew);
            contactId = app.db().contacts().iterator().next().getId();
            return;
        } else {
            //если групп нет, то мы создаем, привязываем к любому контракту и выходим из метода
            if (app.db().groups().size() == 0 ) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test10"));
                GroupData group = app.db().groups().iterator().next();
                groupId = group.getId();
                contactId = app.db().contacts().iterator().next().getId();
                app.goTo().homePageFromAnotherPage();
                app.contact().addToGroup(contactId, groupId);
                return;
            }

            // ищем контракт, привязанный к группе, если находим, то выходим из метода
            Contacts contacts = app.db().contacts();
            Groups groups = app.db().groups();
            for (ContactData contact : contacts) {
                if (contact.getGroups().size() != groups.size()) {
                    for (GroupData group : groups) {
                        if (contact.getGroups().contains(group)) {
                            contactId = contact.getId();
                            groupId = group.getId();
                            return;
                        }
                    }
                }
            }

            // если не нашлось контракта, связанного с какой-либо группой, то выбираем любые группу и контракт и связываем
            GroupData group = app.db().groups().iterator().next();
            groupId = group.getId();
            contactId = app.db().contacts().iterator().next().getId();
            app.goTo().homePageFromAnotherPage();
            app.contact().addToGroup(contactId, groupId);
        }
    }

    @Test
    public void testContactRemoveFromGroup() {
        app.goTo().homePageFromAnotherPage();
        app.contact().setFilterByGroup(groupId);
        Contacts before = app.db().contacts();
        app.contact().removeFromGroup(contactId);
        assertThat(app.contact().count(), equalTo(before.size()-1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));
    }
}
