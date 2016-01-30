package com.cactuslabs.boilerbites;


public class FoodSearcher {
    Earhart earhart = new Earhart();
    Windsor windsor = new Windsor();
    Hillenbrand hillenbrand = new Hillenbrand();
    Wiley wiley = new Wiley();
    Ford ford = new Ford();

    public String getBreakfastCourts(String food) {
        String diningCourts = "";

        if (earhart.contains(food, earhart.getBreakfastItems())) {
            diningCourts = diningCourts + "Earhart,";
        }

        if (windsor.contains(food, windsor.getBreakfastItems())) {
            diningCourts = diningCourts + "Windsor,";
        }

        if (ford.contains(food, ford.getBreakfastItems())) {
            diningCourts = diningCourts + "Ford,";
        }

        if (wiley.contains(food, wiley.getBreakfastItems())) {
            diningCourts = diningCourts + "Wiley,";
        }

        if (hillenbrand.contains(food, hillenbrand.getBreakfastItems())) {
            diningCourts = diningCourts + "Hillenbrand,";
        }

        if (diningCourts.equals("")) {
            return null;
        } else {
            diningCourts = diningCourts.substring(0, diningCourts.length() - 1);
            return diningCourts;
        }


    }

    public String getLunchCourts(String food) {
        String diningCourts = "";
        if (earhart.contains(food, earhart.getLunchItems())) {
            diningCourts = diningCourts + "Earhart,";
        }

        if (windsor.contains(food, windsor.getLunchItems())) {
            diningCourts = diningCourts + "Windsor,";
        }

        if (ford.contains(food, ford.getLunchItems())) {
            diningCourts = diningCourts + "Ford,";
        }

        if (wiley.contains(food, wiley.getLunchItems())) {
            diningCourts = diningCourts + "Wiley,";
        }

        if (hillenbrand.contains(food, hillenbrand.getLunchItems())) {
            diningCourts = diningCourts + "Hillenbrand,";
        }
        if (diningCourts.equals("")) {
            return null;
        } else {
            diningCourts = diningCourts.substring(0, diningCourts.length() - 1);
            return diningCourts;
        }

    }

    public String getDinnerCourts(String food) {
        String diningCourts = "";
        if (wiley.contains(food, wiley.getDinnerItems())) {
            diningCourts = diningCourts + "Wiley,";
        }
        if (ford.contains(food, ford.getDinnerItems())) {
            diningCourts = diningCourts + "Ford,";
        }
        if (windsor.contains(food, windsor.getDinnerItems())) {
            diningCourts = diningCourts + "Windsor,";
        }
        if (earhart.contains(food, earhart.getDinnerItems())) {
            diningCourts = diningCourts + "Earhart,";
        }
        if (hillenbrand.contains(food, hillenbrand.getDinnerItems())) {
            diningCourts = diningCourts + "Hillenbrand,";
        }
        if (diningCourts.equals("")) {
            return null;
        } else {
            diningCourts = diningCourts.substring(0, diningCourts.length() - 1);
            return diningCourts;
        }
    }


}
