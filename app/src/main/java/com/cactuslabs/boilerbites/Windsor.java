import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Windsor extends DiningCourt {


    public Windsor() {
        super();
    }
    public JSONObject getMenu() {
        try {
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
            String date = ft.format(dNow);
            String urlInital = "http://api.hfs.purdue.edu/menus/v1/locations/Windsor/";
            String url = urlInital + date;
            URL website = new URL(url);
            Scanner sc = new Scanner(website.openStream());
            String menuData = "";
            while (sc.hasNext()){
                menuData = menuData + " " + sc.next();
            }
            JSONObject menu = new JSONObject(menuData);
            return menu;
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
