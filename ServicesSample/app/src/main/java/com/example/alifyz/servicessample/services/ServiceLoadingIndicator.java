package com.example.alifyz.servicessample.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class ServiceLoadingIndicator extends IntentService {

    public ServiceLoadingIndicator() {
        super("Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
