package com.cactuslabs.boilerbites;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent i = new Intent(context, NotificationBuilder.class);
            PendingIntent pi = PendingIntent.getBroadcast(context,
                    0, i, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager)
                    context.getSystemService(Context.ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 6);
            calendar.set(Calendar.MINUTE, 00);
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    1000 * 60 * 60 * 24, pi);
            (Toast.makeText(context, "Boot received by BoilerBites", Toast.LENGTH_LONG)).show();
        }
    }
}
