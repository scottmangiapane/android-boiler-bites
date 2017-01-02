package com.cactuslabs.boilerbites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;

import java.util.LinkedList;

public class DataUtil {
    private Context context;
    private SharedPreferences sharedPreferences;

    public DataUtil(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("app_data", Context.MODE_PRIVATE);
    }

    public boolean isEmpty() {
        return getData().size() == 0;
    }

    public void addItem(String item) {
        LinkedList<String> list = getData();
        if (list.contains(item))
            list.remove(item);
        list.push(item);
        setData(list);
    }

    public void removeItem(String item) {
        LinkedList<String> list = getData();
        if (list.contains(item))
            list.remove(item);
        setData(list);
    }

    public void setData(LinkedList<String> data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("size", data.size());
        for (int i = 0; i < data.size(); i++)
            editor.putString("item_" + i, data.get(i));
        editor.apply();
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("REFRESH_LIST"));
    }

    public LinkedList<String> getData() {
        int size = sharedPreferences.getInt("size", 0);
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < size; i++)
            list.add(sharedPreferences.getString("item_" + i, ""));
        return list;
    }
}
