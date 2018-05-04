package com.example.alify.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.alify.bakingapp.R;

/**
 * Created by alify on 3/1/2018.
 */

public class WidgetLogic extends RemoteViewsService  {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetLogicFactory(this, intent);
    }

    class WidgetLogicFactory implements RemoteViewsFactory {

        Context mContext;
        Intent mIntent;
        String mIngredientList = "";

        public WidgetLogicFactory(Context mContext, Intent mIntent) {
            this.mContext = mContext;
            this.mIntent = mIntent;
        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            SharedPreferences preferences = mContext.getSharedPreferences("Pref", mContext.MODE_PRIVATE);
            mIngredientList = preferences.getString("ingredients", "YOU MUST SELECT A RECIPE");
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public RemoteViews getViewAt(int i) {

            SharedPreferences preferences = mContext.getSharedPreferences("Pref", mContext.MODE_PRIVATE);
            mIngredientList = preferences.getString("ingredients", "Empty List, Select a Recipe");

            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.steps_description);
            views.setTextViewText(R.id.main_text, mIngredientList);
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
