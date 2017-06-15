package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.data.JokeTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import static org.junit.Assert.assertTrue;



/**
 * Created by caude on 6/15/2017.
 */

@RunWith(AndroidJUnit4.class)
public class JokeTest {

    @Test
    public void taskJokeTest() {
        JokeTask task = new JokeTask();

        String result;

        try {
            result = task.execute().get();

            assert !result.isEmpty();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
