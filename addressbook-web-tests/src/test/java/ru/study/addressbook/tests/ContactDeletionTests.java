package ru.study.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Dreamer on 07.12.2016.
 */
public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion(){
        app.getContactHelper().selectContact();
        app.getContactHelper().submitDeleteContact();
    }
}
