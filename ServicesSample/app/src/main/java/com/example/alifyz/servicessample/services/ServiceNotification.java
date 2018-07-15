package com.example.alifyz.servicessample.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.alifyz.servicessample.R;

public class ServiceNotification extends IntentService {

    private static final String SERVICE_NAME = "ServiceNotification";
    private static final int NOTIFICATION_ID = 10;

    public ServiceNotification() {
        super(SERVICE_NAME);
    }

    /*
       The instructions inside the onHandleIntent are parsed inside a HandlerThread.
       However, to make it more powerful or more Thread friendly, the usage of AsyncTask
       or any other mechanism of background thread is advised.
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            // Perform some calculation
            Thread.sleep(4000);
            notifyUser();
        } catch (Exception e) {
            // Handle Execption
        }
    }

    /*
        Builds a notification and notify the user when the calculations inside the service are done.
     */
    private void notifyUser() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Service is done!")
                .setChannelId("Default Channel")
                .setContentText("The service already finishes its job");
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }
}
