package com.udacity.gradle.builditbigger.data;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tyler on 5/30/2017.
 */

public class JokeTask extends AsyncTask<String, Void, String> {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String LOG_TAG = JokeTask.class.getSimpleName();

    private final String URI_PREFX = "https://";
    private final String URI_BASE = "localhost:8080";

    private OkHttpClient client = new OkHttpClient();

    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.v(LOG_TAG, "onPostExecute - result: " + s);
    }

    @Override
    protected String doInBackground(String... params) {

        String response = "{ 'response'" + "=" + "'failed' }";

        try {

            RequestBody requestBody = RequestBody.create(JSON, "{ 'name'" + "=" + "'test' }");
            Request request = new Request.Builder()
                                        .url("http://localhost:8080")
                                        .post(requestBody)
                                        .build();

            response = client.newCall(request).execute().body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
