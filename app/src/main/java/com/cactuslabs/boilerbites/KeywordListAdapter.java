package com.cactuslabs.boilerbites;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class KeywordListAdapter extends BaseAdapter {
    private Context context;
    private String[] keywords;

    public KeywordListAdapter(Activity activity, String[] keywordStrings) {
        this.context = activity;
        keywords = keywordStrings;
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
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater
                = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_view_item, null);
        ImageView trashImageView = (ImageView) view.findViewById(R.id.trash_image);
        trashImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return view;
    }
}
