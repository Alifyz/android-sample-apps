package com.example.alifyz.servicessample.services;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.alifyz.servicessample.R;

import java.util.Timer;
import java.util.TimerTask;

public class ServiceLoadingIndicator extends IntentService {

    private static final String CHANEL_ID = "ChanelID";
    private static final int NOTIFICATION_ID = 50;
    private static final int MAX_PROGRESS = 100;
    private static int CURRENT_PROGRESS = 0;

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManagerCompat;

    public ServiceLoadingIndicator() {
        super("Service");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        notificationManagerCompat =
                (NotificationManager) getApplicationContext()
                        .getSystemService(getApplicationContext().NOTIFICATION_SERVICE);

        notificationBuilder = new NotificationCompat.Builder(this, CHANEL_ID);

        notificationBuilder
                .setContentTitle("Notification Progress")
                .setContentText("Downloading something")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_LOW);

        notifyUsers(notificationManagerCompat, notificationBuilder);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Timer timerTask = new Timer();
        timerTask.schedule(new TimerTask() {
            @Override
            public void run() {
                CURRENT_PROGRESS++;
                notifyUsers(notificationManagerCompat, notificationBuilder);
            }
        }, 0, 1000);

        if (CURRENT_PROGRESS >= 100) {
            timerTask.cancel();
        }
    }

    //Implement Notitication Channels for Android Oreo and Above.
    //Ongoing Notification reporting progress.
    private void notifyUsers(NotificationManager m, NotificationCompat.Builder b) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel("CHANNEL101",
                    "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);

            m.createNotificationChannel(channel);
            b.setChannelId("CHANNEL101");
            b.setOnlyAlertOnce(true);

            b.setProgress(MAX_PROGRESS, CURRENT_PROGRESS, false);
            m.notify(NOTIFICATION_ID, notificationBuilder.build());
        } else {
            b.setProgress(MAX_PROGRESS, CURRENT_PROGRESS, false);
            b.setChannelId("CHANNEL101");
            b.setOnlyAlertOnce(true);
            m.notify(NOTIFICATION_ID, notificationBuilder.build());
        }
    }

}
