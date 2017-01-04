package com.cactuslabs.boilerbites;

import android.app.ProgressDialog;
import android.os.AsyncTask;

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

public class WebScraper extends AsyncTask<String, String, JSONObject[]> {
    private HttpURLConnection urlConnection;
    private OverviewActivity activity;
    private ProgressDialog dialog;
    private String date;

    public WebScraper(OverviewActivity activity) {
        this.activity = activity;
        date = (new SimpleDateFormat("MM-dd-yyyy", Locale.US)).format(new Date());
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(activity);
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.setMessage("Please wait...");
        dialog.show();
    }

    @Override
    protected JSONObject[] doInBackground(String... args) {
        JSONObject[] data = null;
        try {
            JSONArray locations = new JSONArray(fetch("https://api.hfs.purdue.edu/menus/v1/locations/"));
            data = new JSONObject[locations.length()];
            for (int i = 0; i < locations.length(); i++) {
                data[i] = new JSONObject(fetch("https://api.hfs.purdue.edu/menus/v1/locations/"
                        + locations.getString(i).replace(" ", "%20") + "/" + "02-05-2016")); // TODO: change to 'date'
                data[i].put("Location", locations.get(i));
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(JSONObject[] data) {
        if (dialog.isShowing())
            dialog.dismiss();
        activity.loadData(data);
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
        return builder.toString();
    }
}
