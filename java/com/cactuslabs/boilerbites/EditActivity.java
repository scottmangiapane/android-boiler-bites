package com.cactuslabs.boilerbites;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.edit_toolbar));
        new MainView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.overview:
                Intent i = new Intent(this, OverviewActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class MainView {
        private Button addButton;
        private List<ListItem> items;
        private ListAdapter adapter;
        private RecyclerView recyclerView;
        private TextView splashText;
        private TextView addTextView;

        public MainView(final EditActivity activity) {
            this.addButton = (Button) activity.findViewById(R.id.add_button);
            this.items = new ArrayList<>();
            this.adapter = new ListAdapter(activity, items);
            this.recyclerView = (RecyclerView) activity.findViewById(R.id.keyword_list);
            this.splashText = (TextView) activity.findViewById(R.id.splash_text);
            this.addTextView = (TextView) activity.findViewById(R.id.add_textbox);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    (new DataUtil(activity)).addItem("" + addTextView.getText());
                }
            });
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new ListDivider(activity));
            recyclerView.setAdapter(adapter);
            LocalBroadcastManager.getInstance(activity).registerReceiver(new ListRefreshReceiver(), new IntentFilter("REFRESH_LIST"));
            LocalBroadcastManager.getInstance(activity).sendBroadcast(new Intent("REFRESH_LIST"));
        }

        public class ListRefreshReceiver extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
                while (!items.isEmpty())
                    items.remove(0);
                DataUtil du = new DataUtil(context);
                LinkedList<String> data = du.getData();
                for (String keyword : data)
                    items.add(new ListItem(keyword));
                if (items.size() == 0)
                    splashText.setVisibility(View.VISIBLE);
                else
                    splashText.setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
