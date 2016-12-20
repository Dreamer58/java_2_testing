package ru.study.mantisbt.model;

/**
 * Created by Dreamer on 19.12.2016.
 */
public class MailMessage {
    public String to;
    public String text;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
