package ru.study.sandbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dreamer on 08.12.2016.
 */
public class Collections {
    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "Python", "PHP"};

        System.out.println();
        for (int i = 0; i < langs.length; i++) {
            System.out.println("Я хочу выучить " + langs[i]);
        }

        System.out.println();
        for (String l : langs) {
            System.out.println("Я хочу выучить " + l);
        }

        System.out.println();
        List<String> languages = new ArrayList<String>();
        languages.add("Java");
        languages.add("C#");
        languages.add("Python");

        for (String l : languages) {
            System.out.println("Я хочу выучить " + l);
        }

    }
}
