package com.cactuslabs.boilerbites;

import android.content.Intent;
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
            case R.id.edit_icon:
                startActivity(new Intent(this, EditActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class OverviewView {
        private LinkedList<String> keywords;
        private TextView overviewBreakfast;
        private TextView overviewLunch;
        private TextView overviewDinner;

        public OverviewView(OverviewActivity activity) {
            this.keywords = (new DataUtil(activity)).getData();
            this.overviewBreakfast = (TextView) activity.findViewById(R.id.overview_breakfast);
            this.overviewLunch = (TextView) activity.findViewById(R.id.overview_lunch);
            this.overviewDinner = (TextView) activity.findViewById(R.id.overview_dinner);
            FoodSearcher foodSearcher = new FoodSearcher();
            String output = "";
            for (String food : keywords) {
                LinkedList<String> breakfastCourts = foodSearcher.searchDiningCourts(1, food);
                output += "\t" + food + "at " + breakfastCourts.toString() + "\n";
            }
            overviewBreakfast.setText(output);
            output = "";
            for (String food : keywords) {
                LinkedList<String> lunchCourts = foodSearcher.searchDiningCourts(1, food);
                output += "\t" + food + "at " + lunchCourts.toString() + "\n";
            }
            overviewLunch.setText(output);
            output = "";
            for (String food : keywords) {
                LinkedList<String> dinnerCourts = foodSearcher.searchDiningCourts(1, food);
                output += "\t" + food + "at " + dinnerCourts.toString() + "\n";
            }
            overviewDinner.setText(output);
        }
    }
}
