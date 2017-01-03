package com.cactuslabs.boilerbites;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

public class WebScraper extends AsyncTask<String, String, String> {
    private HttpURLConnection urlConnection;
    private LinkedList<String> keywords;
    private OverviewActivity activity;
    private ProgressDialog dialog;
    private String diningCourt;
    private TextView overviewBreakfast;
    private TextView overviewLunch;
    private TextView overviewDinner;

    public WebScraper(OverviewActivity activity, String diningCourt) {
        this.keywords = (new DataUtil(activity)).getData();
        this.activity = activity;
        this.diningCourt = diningCourt;
        this.overviewBreakfast = (TextView) activity.findViewById(R.id.overview_breakfast);
        this.overviewLunch = (TextView) activity.findViewById(R.id.overview_lunch);
        this.overviewDinner = (TextView) activity.findViewById(R.id.overview_dinner);
        if (diningCourt == null)
            execute("https://api.hfs.purdue.edu/menus/v1/locations/");
        else {
            String date = (new SimpleDateFormat("MM-dd-yyyy", Locale.US)).format(new Date());
            String url = "https://api.hfs.purdue.edu/menus/v1/locations/" + diningCourt + "/" + "02-03-2016";//date;
            execute(url);
        }
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(activity);
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.setMessage("Please wait...");
        if (diningCourt == null)
            dialog.show();
    }

    @Override
    protected String doInBackground(String... args) {
        StringBuilder result = new StringBuilder();
        try {
            urlConnection = (HttpURLConnection) (new URL(args[0])).openConnection();
            //urlConnection.setRequestProperty("accept", "application/xml");
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null)
                result.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return result.toString();
    }

    @Override
    protected void onPostExecute(String data) {
        if (dialog.isShowing())
            dialog.dismiss();
        try {
            if (diningCourt == null) {
                JSONArray json = new JSONArray(data);
                for (int i = 0; i < json.length(); i++)
                    new WebScraper(activity, json.getString(i).replace(" ", "%20"));
            } else {
                JSONObject json = new JSONObject(data);
                // // TODO: 1/3/17 iterate over json, and return data back to OverviewActivity 
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
