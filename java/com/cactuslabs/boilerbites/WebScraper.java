package com.cactuslabs.boilerbites;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WebScraper extends AsyncTask<String, String, JSONObject> {
    private HttpURLConnection urlConnection;
    private MethodReference[] methodReferences;
    private String date;

    public WebScraper(MethodReference... methodReferences) {
        this.methodReferences = methodReferences;
        this.date = (new SimpleDateFormat("MM-dd-yyyy", Locale.US)).format(new Date());
        Log.w("########", "You made a WebScraper!");
        this.execute();
    }

    @Override
    protected JSONObject doInBackground(String... args) {
        Log.w("########", "The WebScraper starts to do things...");
        JSONObject data = new JSONObject();
        try {
            JSONArray locations = new JSONArray(fetch("https://api.hfs.purdue.edu/menus/v1/locations/"));
            data.put("Date", date);
            data.put("Locations", locations);
            for (int i = 0; i < locations.length(); i++) {
                String url = "https://api.hfs.purdue.edu/menus/v2/locations/"
                        + locations.getString(i).replace(" ", "%20") + "/" + date;
                JSONObject diningCourt = new JSONObject(fetch(url));
                data.put(locations.getString(i), diningCourt);
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        Log.w("########", "The WebScraper finishes doing things...");
        Log.w("########", data.toString());
        return data;
    }

    @Override
    protected void onPostExecute(JSONObject data) {
        Log.w("########", "WebScraper onPostExecute()");
        for (MethodReference methodReference : methodReferences)
            methodReference.run(data);
    }

    private String fetch(String url) throws JSONException, IOException {
        StringBuilder builder = new StringBuilder();
        urlConnection = (HttpURLConnection) (new URL(url)).openConnection();
        InputStream input = new BufferedInputStream(urlConnection.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;
        while ((line = reader.readLine()) != null)
            builder.append(line);
        urlConnection.disconnect();
        Log.w("########", "Fetching...");
        return builder.toString();
    }
}
