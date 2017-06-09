package com.udacity.gradle.builditbigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.udacity.gradle.JokeBuddy;
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
//        Intent intent = new Intent(this, JokeActivity.class);
//
//        // Add string extra could work too
//        // intent.putExtra("EXTRA_TAG", JokeBuddy.getJoke());
//
//        startActivity(intent);

        // Use an AsyncTask to send a request to the JokeServlet backend

        JokeTask jokeTask = new JokeTask();
        jokeTask.execute("JOKE_RANDOM");
    }

    public class TaskReceiver extends BroadcastReceiver {

        public static final String TASK_TAG = "task_receiver_tag";

        @Override
        public void onReceive(Context context, Intent intent) {

            // Who launched the broadcast?
            String tag = intent.getStringExtra(TASK_TAG);

            switch (tag) {
                case "joke_random":
                    String joke = intent.getStringExtra("joke_random");
                    Snackbar.make(findViewById(R.id.root_layout), joke, Snackbar.LENGTH_LONG).show();
                    break;

                default:
                    Log.e(LOG_TAG, "Tag not found: " + tag);
            }
        }
    }
}
