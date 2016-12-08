package ru.study.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.study.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() throws InterruptedException {
        app.getNavigationHelper().gotoGroupsPage();
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("test10", null, null));
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before+1);
    }
}
