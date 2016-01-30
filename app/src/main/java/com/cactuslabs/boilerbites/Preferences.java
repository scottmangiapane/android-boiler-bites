package com.cactuslabs.boilerbites;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Preferences(Context activity) {
        this.context = activity;
        sharedPreferences = activity.getSharedPreferences("com.cactuslabs.boilerbites", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getData() {
        return sharedPreferences.getString("data", "");
    }

    public void setData(String data) {
        editor.putString("data", data);
        editor.commit();
    }

    public boolean isFirstRun() {
        boolean b = sharedPreferences.getBoolean("first_run", true);
        editor.putBoolean("first_run", false);
        editor.commit();
        return b;
    }
}
