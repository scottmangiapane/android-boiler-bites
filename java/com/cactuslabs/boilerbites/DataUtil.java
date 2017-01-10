package com.cactuslabs.boilerbites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

public class DataUtil {
    private Context context;
    private SharedPreferences.Editor editor;
    private LinkedList<String> list;
    private SharedPreferences sharedPreferences;

    public DataUtil(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("app_data", Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
        this.list = getItems();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public void addItem(String item) {
        if (list.contains(item))
            list.remove(item);
        list.push(item);
        setItems(list);
    }

    public void removeItem(String item) {
        if (list.contains(item))
            list.remove(item);
        setItems(list);
    }

    public boolean isItem(String item) {
        for (String listItem : list)
            if (item.contains(listItem))
                return true;
        return false;
    }

    public void setItems(LinkedList<String> data) {
        editor.putInt("size", data.size());
        for (int i = 0; i < data.size(); i++)
            editor.putString("item_" + i, data.get(i));
        editor.apply();
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("REFRESH_LIST"));
    }

    public LinkedList<String> getItems() {
        int size = sharedPreferences.getInt("size", 0);
        LinkedList<String> newList = new LinkedList<>();
        for (int i = 0; i < size; i++)
            newList.add(sharedPreferences.getString("item_" + i, ""));
        return newList;
    }

    public void getCache(final MethodReference runner) {
        String data = sharedPreferences.getString("cache", "");
        if (data.equals("")) {
            new WebScraper(runner, new MethodReference() {
                @Override
                public void run(JSONObject data) {
                    editor.putString("cache", data.toString());
                    editor.commit();
                }
            });
            return;
        }
        JSONObject json = null;
        String date = "";
        try {
            json = new JSONObject(data);
            date = json.getString("Date");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!date.equals((new SimpleDateFormat("MM-dd-yyyy", Locale.US)).format(new Date()))) {
            editor.remove("cache");
            editor.commit();
            getCache(runner);
            return;
        }
        runner.run(json);
    }
}
