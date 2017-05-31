package com.udacity.gradle;

public class JokeBuddy {

    private static final String[] jokes = {
            "Yo mama", "This app",
            "My life", "Memes",
    };

    public static String getJoke() {
        int index = (int)(jokes.length * Math.random());
        return jokes[index];
    }
}
