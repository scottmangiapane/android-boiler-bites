package com.cactuslabs.boilerbites;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainView {
    private MainActivity activity;
    private EditText addEditText;
    private Button addButton;
    private CardView cardView;
    private ListView keywordListView;
    private TextView splashTextView;
    private Preferences preferences;
    private static String splashString;

    public MainView(MainActivity activity) {
        this.activity = activity;
        this.addEditText = (EditText) activity.findViewById(R.id.add_edit_text);
        this.addButton = (Button) activity.findViewById(R.id.add_button);
        this.cardView = (CardView) activity.findViewById(R.id.card_view);
        this.keywordListView = (ListView) activity.findViewById(R.id.keyword_list_view);
        this.splashTextView = (TextView) activity.findViewById(R.id.splash_text_view);
        this.preferences = new Preferences(activity);
        splashString = "Nothing here!\nAdd a food above to get started.";
        setUp();
    }

    private void setUp() {
        addEditText.requestFocus();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = String.valueOf(addEditText.getText());
                if (!keyword.equals("")) {
                    addEditText.setText("");
                    preferences.setData(preferences.getData() + keyword + "\t");
                    cardView.setVisibility(View.VISIBLE);
                    splashTextView.setText("");
                    activity.refresh();
                }
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (activity, R.layout.list_view_item, R.id.list_text, preferences.getData().split("\t"));
        keywordListView.setAdapter(adapter);
        keywordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (id == R.id.trash_image) {
                    String text = keywordListView.getItemAtPosition(position) + "\t";
                    preferences.setData(preferences.getData().replaceFirst(text, ""));
                    if (preferences.getData().equals("")) {
                        cardView.setVisibility(View.INVISIBLE);
                        splashTextView.setText(splashString);
                    }
                    activity.refresh();
                }
            }
        });
        if (preferences.getData().equals("")) {
            cardView.setVisibility(View.INVISIBLE);
            splashTextView.setText(splashString);
        }
    }
}
