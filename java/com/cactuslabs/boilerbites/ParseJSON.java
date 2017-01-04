package com.cactuslabs.boilerbites;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.LinkedList;

public class ParseJSON {
    public LinkedList<String> parseMenu(JSONArray menu) {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < menu.length(); i++)
            try {
                JSONArray items = menu.getJSONObject(i).getJSONArray("Items");
                for (int j = 0; j < items.length(); j++)
                    list.add(items.getJSONObject(j).getString("Name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return list;
    }
}
