package com.cactuslabs.boilerbites;

import android.widget.TextView;

public class OverviewView {
    private OverviewActivity activity;
    Preferences preferences;
    private String[] keywords;
    private TextView overviewTextView;

    public OverviewView(OverviewActivity activity) {
        this.activity = activity;
        this.preferences = new Preferences(activity);
        this.keywords = preferences.getData().split("\t");
        this.overviewTextView = (TextView) activity.findViewById(R.id.overview_text_view);
        //setUp();
    }

    private void setUp() {
        String output = "";
        FoodSearcher foodSearcher = new FoodSearcher();
        boolean firstBreakfast = false;
        boolean firstLunch = false;
        boolean firstDinner = false;
        for (int x = 0; x < keywords.length; x++) {
            String breakfastCourts = foodSearcher.getBreakfastCourts(keywords[x]);
            if (breakfastCourts != null && !firstBreakfast) {
                firstBreakfast = true;
                output = output + "Breakfast:\n\t\t" + keywords[x] + "at " + breakfastCourts + "\n\t\t";
            } else if (breakfastCourts != null && firstBreakfast) {
                output = output + keywords[x] + "at " + breakfastCourts + "\n\t\t";
            }
        }
        if (output != "") {
            output = output + "\n\n";
        }
        for (int x = 0; x < keywords.length; x++) {
            String lunchCourts = foodSearcher.getLunchCourts(keywords[x]);
            if (lunchCourts != null && !firstLunch) {
                firstLunch = true;
                output = output + "Lunch:\n\t\t" + keywords[x] + "at " + lunchCourts + "\n\t\t";
            } else if (lunchCourts != null && firstLunch) {
                output = output + keywords[x] + "at " + lunchCourts + "\n\t\t";
            }
        }
        if (output != "") {
            output = output + "\n\n";
        }
        for (int x = 0; x < keywords.length; x++) {
            String dinnerCourts = foodSearcher.getDinnerCourts(keywords[x]);
            if (dinnerCourts != null && !firstDinner) {
                firstDinner = true;
                output = output + "Dinner:\n\t\t" + keywords[x] + "at " + dinnerCourts + "\n\t\t";
            } else if (dinnerCourts != null && firstDinner) {
                output = output + keywords[x] + "at " + dinnerCourts + "\n\t\t";
            }
        }
        overviewTextView.setText(output);
    }
}
