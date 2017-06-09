package com.udacity.gradle.builditbigger.data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.caude.myapplication.backend.myApi.MyApi;
import com.google.appengine.repackaged.com.google.api.client.http.javanet.NetHttpTransport;
import com.google.appengine.repackaged.com.google.api.client.json.JsonFactory;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.google.api.

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

    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.v(LOG_TAG, "onPostExecute - result: " + s);
    }

    @Override
    protected String doInBackground(String... params) {

        if (myApiService == null) {

            // Builder method call types > HttpTransport, JsonFactory, HttpRequestInitializer
//            MyApi.Builder builder = new MyApi.Builder(new NetHttpTransport(), new JsonFactory(), null);
        }

        String response = "{ 'response'" + "=" + "'failed' }";



        return response;
    }
}
