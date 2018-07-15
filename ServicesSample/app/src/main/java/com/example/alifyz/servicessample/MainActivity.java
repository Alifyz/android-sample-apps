package com.example.alifyz.servicessample;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alifyz.servicessample.services.ServiceNotification;
import com.example.alifyz.servicessample.services.ServicePendingIntent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, ServiceNotification.class));
    }

    /*
      This method create a Service with a PendingIntent as an Extra Parcelable.
      the Service does not perform any fancy calculation, but it serve as an example of using a
      PendingIntent to launch an Activity when the results of a Service has been done.
      Use this method with caution, an infinity loop may occur.
     */
    private void startServicePendingIntent(PendingIntent pendingIntent) {
        Intent service = new Intent(this, ServicePendingIntent.class);
        service.putExtra("PENDING", pendingIntentBuilder());
        startService(service);
    }

    private PendingIntent pendingIntentBuilder() {
        Intent wrapIntent = new Intent(this, MainActivity.class);
        return PendingIntent.getActivity(this, 0, wrapIntent, PendingIntent.FLAG_UPDATE_CURRENT );
    }
}
