package ru.study.sandbox;

/**
 * Created by Dreamer on 07.12.2016.
 */
public class Equality {
    public static void main(String[] args){
        String s1 = "firefox 2.0";
        String s2 = "fire" + "fox " + Math.sqrt(4.0);

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }
}
