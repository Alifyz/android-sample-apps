package com.example.alifyz.servicessample.services;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;


public class ServicePendingIntent extends IntentService {

    private static final String SERVICE_NAME = "PendingIntentServoce";
    private static final int PENDING_RESULT_CODE = 0;

    public ServicePendingIntent() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null) {
            PendingIntent pendingIntent = intent.getParcelableExtra("PENDING");
            try {
                //Background Work!
                //Task that can take processing power and needs to be done outside the main thread.
                Thread.sleep(4000); // Just for testing purposes!
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //When the Service is done and all calculations were finished!
                //Launch the Activity to the User.
                try {
                    pendingIntent.send(this, PENDING_RESULT_CODE, new Intent());
                    stopSelf();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
