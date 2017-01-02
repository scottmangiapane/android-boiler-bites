package com.cactuslabs.boilerbites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.LinkedList;

public class OverviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.overview_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new OverviewView(this);
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
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class OverviewView {
        private OverviewActivity activity;
        private LinkedList<String> keywords;
        private TextView overviewTextView;

        public OverviewView(OverviewActivity activity) {
            this.activity = activity;
            this.keywords = (new DataUtil(activity)).getData();
            this.overviewTextView = (TextView) activity.findViewById(R.id.overview_text);
            FoodSearcher foodSearcher = new FoodSearcher();
            String output = "Breakfast:\n";
            for (String food : keywords) {
                LinkedList<String> breakfastCourts = foodSearcher.searchDiningCourts(1, food);
                output += "\t" + food + "at " + breakfastCourts.toString() + "\n";
            }
            output += "Lunch:\n";
            for (String food : keywords) {
                LinkedList<String> lunchCourts = foodSearcher.searchDiningCourts(1, food);
                output += "\t" + food + "at " + lunchCourts.toString() + "\n";
            }
            output += "Dinner:\n";
            for (String food : keywords) {
                LinkedList<String> dinnerCourts = foodSearcher.searchDiningCourts(1, food);
                output += "\t" + food + "at " + dinnerCourts.toString() + "\n";
            }
            overviewTextView.setText(output);
        }
    }
}