package com.udacity.gradle.builditbigger.data;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Tyler on 5/31/2017.
 */

public class JokeIntentService extends IntentService {

    public static final String INTENT_TAG = "intent_tag";
    public static final String JOKE_TAG = "joke_tag";
    public static final String JOKE_RAND = "joke type: random";

    public JokeIntentService() {
        super(JokeIntentService.class.getName());
    }

    public JokeIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        JokeTask jokeTask = new JokeTask();

        if (intent.getStringExtra(INTENT_TAG).equals(JOKE_TAG)) {
            jokeTask.execute(JOKE_RAND);
        }
    }
}
