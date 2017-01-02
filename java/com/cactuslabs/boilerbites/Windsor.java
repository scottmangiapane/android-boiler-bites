package com.cactuslabs.boilerbites;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Windsor extends DiningCourt {
    public Windsor() {
        super();
    }

    public void getMenu() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
        String date = ft.format(dNow);
        String urlInital = "http://api.hfs.purdue.edu/menus/v1/locations/Windsor/";
        String url = urlInital + date;
        new Windsor().execute(url);
    }
}
