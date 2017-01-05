package com.cactuslabs.boilerbites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class OverviewActivity extends AppCompatActivity {
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.overview_toolbar));
        activity = this;
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, RawTextActivity.class));
            }
        });
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
}
