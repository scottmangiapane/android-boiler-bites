package com.cactuslabs.boilerbites;

import android.content.Context;
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

public class RawTextActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raw_text_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.raw_text_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.context = this;
        (new DataUtil(this)).getCache(new MethodReference() {
            @Override
            public void run(JSONObject data) {
                DataUtil dataUtil = new DataUtil(context);
                ParseJSON parser = new ParseJSON();
                String text = "";
                try {
                    JSONArray food = data.getJSONArray("Food");
                    for (int i = 0; i < food.length(); i++) {
                        text += food.getJSONObject(i).getString("Location") + "\n";
                        JSONArray meals = food.getJSONObject(i).getJSONArray("Meals");
                        for (int j = 0; j < meals.length(); j++) {
                            try {
                                JSONObject meal = meals.getJSONObject(j);
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
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.simple_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
