package com.cactuslabs.boilerbites;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Preferences(Activity activity) {
        sharedPreferences = activity.getSharedPreferences("keywords", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getData() {
        return sharedPreferences.getString("com.cactuslabs.notify.data", "");
    }

    public void setData(String data) {
        editor.putString("com.cactuslabs.notify.data", data);
        editor.commit();
    }
}
