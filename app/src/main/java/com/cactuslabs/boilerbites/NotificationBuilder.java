package com.cactuslabs.boilerbites;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class NotificationBuilder extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Preferences preferences = new Preferences(context);
        String[] keywords = preferences.getData().split("\t");
	 FoodSearcher foodSearcher = new FoodSearcher();
        boolean foundfoods = false;
        for (int x = 0; x < keywords.length; x++){
            if(foodSearcher.getBreakfastCourts(keywords[x]) != null || foodSearcher.getLunchCourts(keywords[x]) != 
                    null || foodSearcher.getDinnerCourts(keywords[x]) != null){
                    foundfoods = true;
                    break;
            }
        }
        if (foundfoods) { /*true if favorite foods (keywords) are being served*/
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_local_dining_white_24dp)
                            .setContentTitle("BoilerBites")
                            .setContentText("Your favorite foods are being served.");
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(OverviewActivity.class);
            stackBuilder.addNextIntent(new Intent(context, OverviewActivity.class));
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(resultPendingIntent);
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify((int) (Math.random() * 10000), builder.build());
        }
    }
}
