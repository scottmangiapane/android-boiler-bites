package com.cactuslabs.boilerbites;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class DiningCourt extends AsyncTask<String, String, String> {
    private JSONObject menu;
    private String name;

    public DiningCourt(String name) {
        this.name = name;
    }

    public void getMenu() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
        String date = ft.format(dNow);
        String url = "http://api.hfs.purdue.edu/menus/v1/locations/" + name + "/" + date;
        execute(url);
    }

    @Override
    protected String doInBackground(String... url) {
        try {
            URL website = new URL(url[0]);
            Scanner sc = new Scanner(website.openStream());
            String menuData = "";
            while (sc.hasNext()) {
                menuData = menuData + " " + sc.next();
            }
            return menuData;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            super.onPostExecute(result);
            JSONObject menu = new JSONObject(result);
            this.menu = menu;
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
    }

    public String[] getBreakfastItems() {
        try {
            String[] breakfastItems = new String[0];
            int numitems = 0;
            JSONArray breakfast = menu.getJSONArray("Breakfast");
            if (breakfast == null || breakfast.length() == 0) {
                breakfastItems = Arrays.copyOf(breakfastItems, breakfastItems.length + 1);
                breakfastItems[0] = "Not Serving";
                return breakfastItems;
            }
            for (int i = 0; i < breakfast.length(); i++) {
                JSONArray items = breakfast.getJSONObject(i).getJSONArray("Items");
                for (int x = 0; x < items.length(); x++) {
                    breakfastItems = Arrays.copyOf(breakfastItems, breakfastItems.length + 1);
                    breakfastItems[numitems] = items.getJSONObject(x).getString("Name");
                    numitems++;
                }
            }
            return breakfastItems;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] getLunchItems() {
        try {
            String[] lunchItems = new String[0];
            int numitems = 0;
            JSONArray lunch = menu.getJSONArray("Lunch");
            if (lunch == null || lunch.length() == 0) {
                lunchItems = Arrays.copyOf(lunchItems, lunchItems.length + 1);
                lunchItems[0] = "Not Serving";
                return lunchItems;
            }
            for (int i = 0; i < lunch.length(); i++) {
                JSONArray items = lunch.getJSONObject(i).getJSONArray("Items");
                for (int x = 0; x < items.length(); x++) {
                    lunchItems = Arrays.copyOf(lunchItems, lunchItems.length + 1);
                    lunchItems[numitems] = items.getJSONObject(x).getString("Name");
                    numitems++;
                }
            }
            return lunchItems;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] getDinnerItems() {
        try {
            String[] dinnerItems = new String[0];
            int numitems = 0;
            JSONArray dinner = menu.getJSONArray("Dinner");
            if (dinner == null || dinner.length() == 0) {
                dinnerItems = Arrays.copyOf(dinnerItems, dinnerItems.length + 1);
                dinnerItems[0] = "Not Serving";
                return dinnerItems;
            }
            for (int i = 0; i < dinner.length(); i++) {
                JSONArray items = dinner.getJSONObject(i).getJSONArray("Items");
                for (int x = 0; x < items.length(); x++) {
                    dinnerItems = Arrays.copyOf(dinnerItems, dinnerItems.length + 1);
                    dinnerItems[numitems] = items.getJSONObject(x).getString("Name");
                    numitems++;
                }
            }
            return dinnerItems;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean contains(String food, String[] meal) {
        food = food.trim();
        if (meal == null) {
            return false;
        }
        for (int x = 0; x < meal.length; x++) {
            if (meal[x].equalsIgnoreCase(food)) {
                return true;
            }
        }
        return false;
    }

    public String[] containsKeyword(String keyword, String[] meal) {
        if (keyword.equals("")) {
            return new String[0];
        }
        keyword = keyword.trim();
        keyword = keyword.toLowerCase();
        String[] keywordsarray = new String[0];
        int numitems = 0;
        if (meal == null) {
            return new String[0];
        }
        for (int x = 0; x < meal.length; x++) {
            if (meal[x].toLowerCase().contains(keyword)) {
                keywordsarray = Arrays.copyOf(keywordsarray, keywordsarray.length + 1);
                keywordsarray[numitems] = meal[x];
                numitems++;
            }
        }
        return keywordsarray;
    }
}
