package com.cactuslabs.boilerbites;

import java.util.LinkedList;

public class FoodSearcher {
    private DiningCourt[] diningCourts = {
            new DiningCourt("Earhart"),
            new DiningCourt("Ford"),
            new DiningCourt("Hillenbrand"),
            new DiningCourt("Wiley"),
            new DiningCourt("Windsor"),
    };

    public LinkedList<String> searchDiningCourts(int meal, String food) {
        LinkedList<String> data = new LinkedList<>();
        for (DiningCourt dc : diningCourts)
            switch (meal) {
                case 1:
                    if (dc.contains(food, dc.getBreakfastItems()))
                        data.add(dc.getName());
                    break;
                case 2:
                    if (dc.contains(food, dc.getLunchItems()))
                        data.add(dc.getName());
                    break;
                case 3:
                    if (dc.contains(food, dc.getDinnerItems()))
                        data.add(dc.getName());
                    break;
            }
        return data;
    }
}
