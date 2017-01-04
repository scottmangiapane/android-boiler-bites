package com.cactuslabs.boilerbites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class OverviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.overview_toolbar));
        WebScraper webScraper = new WebScraper(this);
        webScraper.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overview_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_icon:
                startActivity(new Intent(this, EditActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadData(JSONObject[] data) {
        String breakfastText = "";
        String lunchText = "";
        String dinnerText = "";
        DataUtil dataUtil = new DataUtil(this);
        ParseJSON parser = new ParseJSON();
        try {
            for (JSONObject json : data) {
                LinkedList<String> breakfast = parser.parseMenu(json.getJSONArray("Breakfast"));
                for (String item : breakfast)
                    if (dataUtil.isItem(item))
                        breakfastText += item + " at " + json.getString("Location") + "\n";
                LinkedList<String> lunch = parser.parseMenu(json.getJSONArray("Lunch"));
                for (String item : lunch)
                    if (dataUtil.isItem(item))
                        lunchText += item + " at " + json.getString("Location") + "\n";
                LinkedList<String> dinner = parser.parseMenu(json.getJSONArray("Dinner"));
                for (String item : dinner)
                    if (dataUtil.isItem(item))
                        dinnerText += item + " at " + json.getString("Location") + "\n";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (breakfastText.length() > 0)
            ((TextView) findViewById(R.id.overview_breakfast)).setText(breakfastText);
        if (lunchText.length() > 0)
            ((TextView) findViewById(R.id.overview_lunch)).setText(lunchText);
        if (dinnerText.length() > 0)
            ((TextView) findViewById(R.id.overview_dinner)).setText(dinnerText);
    }
}
