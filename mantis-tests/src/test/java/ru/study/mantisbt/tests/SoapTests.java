package ru.study.mantisbt.tests;

import org.testng.annotations.Test;
import ru.study.mantisbt.model.Issue;
import ru.study.mantisbt.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by Dreamer on 22.12.2016.
 */
public class SoapTests extends TestBase{
    @Test
    public void testGetProjects() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(app.soap().getIssueIdBySummary("Test issue1"));
        Set<Project> projects = app.soap().getProgects();
        System.out.println(projects.size());
        for ( Project project: projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProgects();
        Issue issue = new Issue().withSummary("Test issue1").withDescription("Test issue description")
                .withProject(projects.iterator().next());
        Issue created = app.soap().addIsue(issue);
        assertEquals(issue.getSummary(), created.getSummary());

    }
}
