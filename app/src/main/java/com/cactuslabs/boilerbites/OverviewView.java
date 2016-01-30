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
        setUp();
    }

    private void setUp() {
        String output = "";

        //todo make string output look like the model below (all the foods are stored in keywords)
        //Breakfast:\n\t\ttacos at Earhart\n\t\tsugar cookies at Windsor and Wiley\n\nLunch:\n\t\tLasagna at Wiley\n\t\tTurkey burgers at Ford and Earhart\n\nDinner\n\t\t--

        overviewTextView.setText(output);
    }
}
