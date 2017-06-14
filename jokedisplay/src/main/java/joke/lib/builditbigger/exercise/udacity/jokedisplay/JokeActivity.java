package joke.lib.builditbigger.exercise.udacity.jokedisplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.udacity.gradle.JokeBuddy;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_TAG = "joke_intent_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();
        String joke = intent.getStringExtra(JOKE_TAG);

        if (joke != null && !joke.isEmpty()) {
            TextView joke_textview = (TextView) findViewById(R.id.joke_textview);
            joke_textview.setText(joke);
        }
    }
}
