package com.example.alify.bakingapp;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;

import android.content.ComponentName;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;

import android.widget.RemoteViews;

import com.example.alify.bakingapp.widget.WidgetLogic;


/**
 * Implementation of App Widget functionality.
 */
public class BakinWidgetApp extends AppWidgetProvider {



    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, SharedPreferences preferences) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bakin_widget_app);
        String ingredientList = "";

        if(preferences.contains("title")) {
            views.setTextViewText(R.id.widget_ingredient_title, preferences.getString("title", "Empty Recipe"));
        } else {
            views.setTextViewText(R.id.widget_ingredient_title, "Empty Recipe");
        }

        if(preferences.contains("ingredients")) {
            ingredientList = preferences.getString("ingredients", "No Recipe Selected");
        }else {
            ingredientList = "NOT INITIALIZED";
        }


        Intent remoteViewIntent = new Intent(context, WidgetLogic.class);
        remoteViewIntent.putExtra("ingredients", ingredientList);

        views.setRemoteAdapter(R.id.widget_ingredient, remoteViewIntent);

        Intent mainRecipe = new Intent(context, MainActivity.class);
        PendingIntent mainRecipePendingIntent = PendingIntent.getActivity(context, 0, mainRecipe, 0);

        views.setOnClickPendingIntent(R.id.widget_ingredient_title, mainRecipePendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        SharedPreferences preferences = context.getSharedPreferences("Pref", context.MODE_PRIVATE);
        for (int appWidgetId : appWidgetIds) {
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_ingredient);
            updateAppWidget(context, appWidgetManager, appWidgetId, preferences);

        }
    }

    @Override
    public void onEnabled(Context context) {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName BakingWidgetComp = new ComponentName(context.getApplicationContext(),
                BakinWidgetApp.class);
        int[] widgetIds = appWidgetManager.getAppWidgetIds(BakingWidgetComp);
        if(intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            onUpdate(context, appWidgetManager, widgetIds);
        }
    }

    @Override
    public void onDisabled(Context context) {

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
            SharedPreferences preferences = context.getSharedPreferences("Pref", context.MODE_PRIVATE);
            SharedPreferences.Editor editPreferences = preferences.edit();
            editPreferences.clear();
            editPreferences.apply();
    }
}

