package ru.study.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.study.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dreamer on 11.12.2016.
 */
public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contract count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else {
            System.out.println(String.format("Unrecognized format %s", format));
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i=0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirstname(String.format("first name%s", i))
                    .withMiddlename(String.format("middle name%s", i))
                    .withLastname(String.format("last name%s", i))
                    .withNickname(String.format("nickname%s", i))
                    .withTitle(String.format("title%s", i))
                    .withCompany(String.format("company%s", i))
                    .withAddress(String.format("address%s", i))
                    .withHomePhone("4950000001")
                    .withMobilePhone("9270126354")
                    .withWorkPhone("4951212121")
                    .withEmail1(String.format("test%s@mail.ru", i))
                    .withEmail2(String.format("test%s@gmail.com", i))
                    .withEmail3(String.format("test%s@list.ru", i)));
        }
        return contacts;
    }
}
