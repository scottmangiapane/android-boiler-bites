package com.cactuslabs.boilerbites;
import org.json.JSONObject;

public interface DiningCourt {
    public JSONObject getMenu();
    public String [] getBreakfastItems();
    public String [] getLunchItems();
    public String [] getDinnerItems();
    public boolean contains (String food, String[] meal);
    public String[] containsKeyword(String keyword, String[] meal);
}
