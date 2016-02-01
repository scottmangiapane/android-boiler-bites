package com.cactuslabs.boilerbites;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Earhart extends DiningCourt  {

    public Earhart(){
        super();
    }
    public void getMenu() {
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
            String date = ft.format(dNow);
            String urlInital = "http://api.hfs.purdue.edu/menus/v1/locations/Earhart/";
            String url = urlInital + date;
            new Earhart().execute(url);

    }

}
