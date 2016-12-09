package ru.study.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.study.addressbook.model.GroupData;
import ru.study.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Dreamer on 07.12.2016.
 */
public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test10"));
        }
    }

    @Test(enabled = true)
    public void testGroupModification() throws InterruptedException {
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName("test2")
                .withHeader("test02")
                .withFooter("test03");
        app.group().modify(group);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

}
