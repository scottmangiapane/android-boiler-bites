package com.cactuslabs.boilerbites;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class ParseJSON {
    public LinkedList<String> parseMeal(JSONObject meal) {
        LinkedList<String> list = new LinkedList<>();
        try {
            JSONArray stations = meal.getJSONArray("Stations");
            for (int i = 0; i < stations.length(); i++) {
                JSONArray items = stations.getJSONObject(i).getJSONArray("Items");
                for (int j = 0; j < items.length(); j++)
                    list.add(items.getJSONObject(j).getString("Name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
