package com.cactuslabs.boilerbites;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class KeywordListAdapter extends BaseAdapter {
    private Context context;
    private MainView mainView;
    private String[] keywords;

    public KeywordListAdapter(Activity activity, MainView mainView, String[] keywordStrings) {
        this.context = activity;
        this.mainView = mainView;
        this.keywords = keywordStrings;
    }

    @Override
    public int getCount() {
        return keywords.length;
    }

    @Override
    public Object getItem(int position) {
        return keywords[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater
                = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_view_item, null);
        TextView listText = (TextView) view.findViewById(R.id.list_text);
        ImageView trashImageView = (ImageView) view.findViewById(R.id.trash_image_view);
        listText.setText(keywords[position]);
        trashImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainView.deleteKeyword(position);
            }
        });
        return view;
    }
}
