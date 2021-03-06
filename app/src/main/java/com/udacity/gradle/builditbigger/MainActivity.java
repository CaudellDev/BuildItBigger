package com.udacity.gradle.builditbigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.udacity.gradle.builditbigger.data.JokeTask;

import joke.lib.builditbigger.exercise.udacity.jokedisplay.JokeActivity;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private TaskReceiver mTaskReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTaskReceiver = new TaskReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(mTaskReceiver, new IntentFilter(TaskReceiver.TASK_TAG));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        // Use an AsyncTask to send a request to the JokeServlet backend

        JokeTask jokeTask = new JokeTask();
        jokeTask.setContext(this);
        jokeTask.execute("JOKE_RANDOM");
    }

    public class TaskReceiver extends BroadcastReceiver {

        public static final String TASK_TAG = "task_receiver_tag";

        public static final String JOKE_RND = "joke_random";

        @Override
        public void onReceive(Context context, Intent intent) {

            // Who launched the broadcast?
            String tag = intent.getStringExtra(TASK_TAG);

            switch (tag) {
                case JOKE_RND:
                    String joke = intent.getStringExtra(JOKE_RND);

                    Intent intentAct = new Intent(context, JokeActivity.class);
                    intentAct.putExtra(JokeActivity.JOKE_TAG, joke);
                    context.startActivity(intentAct);

                    break;

                default:
                    Log.e(LOG_TAG, "Tag not found: " + tag);
            }
        }
    }
}
