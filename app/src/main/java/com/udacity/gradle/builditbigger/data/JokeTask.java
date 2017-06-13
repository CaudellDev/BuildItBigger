package com.udacity.gradle.builditbigger.data;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.caude.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.udacity.gradle.builditbigger.MainActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class JokeTask extends AsyncTask<String, Void, String> {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String LOG_TAG = JokeTask.class.getSimpleName();

    private final String URI_PREFX = "https://";
    private final String URI_BASE = "localhost:8080";

    private OkHttpClient client = new OkHttpClient();
    private MyApi myApiService = null;
    private Context mContext;

    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.v(LOG_TAG, "onPostExecute - result: " + s);

        Intent intent = new Intent();
        intent.setAction(MainActivity.TaskReceiver.TASK_TAG);
        intent.putExtra("debug", s);

        if (mContext != null) {
            mContext.sendBroadcast(intent);
        }
    }

    @Override
    protected String doInBackground(String... params) {

        if (myApiService == null) {

            // Builder method call types > HttpTransport, JsonFactory, HttpRequestInitializer
            MyApi.Builder builder =
                    new MyApi.Builder(AndroidHttp.newCompatibleTransport(), AndroidJsonFactory.getDefaultInstance(), null)
                            .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                                    request.setDisableGZipContent(true);
                                }
                            });

            myApiService = builder.build();
        }


        String value = params[0];

        try {
            return myApiService.getRandomJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "{ 'response'" + "=" + "'failed' }";
    }

    public void setContext(Context context) {
        mContext = context;
    }
}
