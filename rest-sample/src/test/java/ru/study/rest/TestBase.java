package ru.study.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

/**
 * Created by Dreamer on 23.12.2016.
 */
public class TestBase {
    protected Executor getExecutor() {
        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
    }
    boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/"+issueId+".json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0);
        return !issues.getAsJsonObject().get("state_name").getAsString().equals("Closed");
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
