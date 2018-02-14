package com.example.alify.bakingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.HashMap;

/**
 * Created by alify on 2/14/2018.
 */

public class IngredientsAdapter extends ArrayAdapter<HashMap<String, String>>{

    public IngredientsAdapter(@NonNull Context context, int resource, @NonNull HashMap<String, String>[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
