package com.cactuslabs.boilerbites;

import java.util.Calendar;

public class Clock {
    private Calendar c = Calendar.getInstance();

    public long currentTimeLong() {
        c.setTimeInMillis(System.currentTimeMillis());
        return getTimeMillis(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
    }

    public long currentDateLong() {
        c.setTimeInMillis(System.currentTimeMillis());
        return getDateMillis(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
    }

    public long currentTimeDate() {
        return System.currentTimeMillis();
    }

    public long getTimeMillis(int hour, int minute) {
        c.setTimeInMillis(0);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        return c.getTimeInMillis();
    }

    public long getDateMillis(int year, int month, int day) {
        c.setTimeInMillis(0);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTimeInMillis();
    }

    public String getTimeString(long millis) {
        c.setTimeInMillis(millis);
        return getTimeString(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
    }

    public String getDateString(long millis) {
        c.setTimeInMillis(millis);
        return getDateString(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }

    public String getTimeString(int hour, int minute) {
        int h = hour;
        if (h != 12)
            h %= 12;
        String min = "" + minute;
        if (min.length() < 2)
            min = "0" + min;
        return "" + h + ":" + min + " " + timeSuffix(hour);
    }

    public String getDateString(int year, int month, int day) {
        return monthString(month) + " " + day + ", " + year;
    }

    public int year() {
        c.setTimeInMillis(System.currentTimeMillis());
        return c.get(Calendar.YEAR);
    }

    public int month() {
        c.setTimeInMillis(System.currentTimeMillis());
        return c.get(Calendar.MONTH);
    }

    public int day() {
        c.setTimeInMillis(System.currentTimeMillis());
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public int hour() {
        c.setTimeInMillis(System.currentTimeMillis());
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public int minute() {
        c.setTimeInMillis(System.currentTimeMillis());
        return c.get(Calendar.MINUTE);
    }

    public int year(long millis) {
        c.setTimeInMillis(millis);
        return c.get(Calendar.YEAR);
    }

    public int month(long millis) {
        c.setTimeInMillis(millis);
        return c.get(Calendar.MONTH);
    }

    public int day(long millis) {
        c.setTimeInMillis(millis);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public int hour(long millis) {
        c.setTimeInMillis(millis);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public int minute(long millis) {
        c.setTimeInMillis(millis);
        return c.get(Calendar.MINUTE);
    }

    public String monthString(int month) {
        switch (month) {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
        }
        return "";
    }

    public String shortMonthString(int month) {
        switch (month) {
            case 0:
                return "Jan";
            case 1:
                return "Feb";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "Aug";
            case 8:
                return "Sept";
            case 9:
                return "Oct";
            case 10:
                return "Nov";
            case 11:
                return "Dec";
        }
        return "";
    }

    private String timeSuffix(int hour) {
        if (hour >= 0 && hour < 12)
            return "AM";
        if (hour >= 12 && hour < 24)
            return "PM";
        return "";
    }
}
