package com.cactuslabs.boilerbites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
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
            case R.id.settings_icon:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadData(JSONObject[] data) {
        DataUtil dataUtil = new DataUtil(this);
        ParseJSON parser = new ParseJSON();
        String text = "";
        try {
            for (JSONObject diningCourt : data) {
                text += diningCourt.getString("Location") + "\n";
                JSONArray meals = diningCourt.getJSONArray("Meals");
                for (int i = 0; i < meals.length(); i++) {
                    try {
                        JSONObject meal = meals.getJSONObject(i);
                        String time = meal.getJSONObject("Hours").getString("StartTime") + " - "
                                + meal.getJSONObject("Hours").getString("EndTime");
                        text += "    " + meal.getString("Name") + " (" + time + ")" + "\n";
                        LinkedList<String> mealItems = parser.parseMeal(meal);
                        for (String item : mealItems)
                            if (dataUtil.isItem(item))
                                text += "        " + item + "\n";
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (text.length() > 0)
            ((TextView) findViewById(R.id.overview_text)).setText(text);
    }
}
