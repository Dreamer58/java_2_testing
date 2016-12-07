package ru.study.addressbook.tests;

import org.testng.annotations.Test;
import ru.study.addressbook.model.GroupData;

/**
 * Created by Dreamer on 07.12.2016.
 */
public class GroupModificationTests extends TestBase{

    @Test
    public void testGroupModification() throws InterruptedException {
        app.getNavigationHelper().gotoGroupsPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test10", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData(null, "test02", "test03"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
