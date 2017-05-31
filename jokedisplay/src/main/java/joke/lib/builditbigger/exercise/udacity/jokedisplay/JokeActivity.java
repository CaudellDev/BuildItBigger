package joke.lib.builditbigger.exercise.udacity.jokedisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.udacity.gradle.JokeBuddy;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView joke_textview = (TextView) findViewById(R.id.joke_textview);
        joke_textview.setText(JokeBuddy.getJoke());
    }
}
