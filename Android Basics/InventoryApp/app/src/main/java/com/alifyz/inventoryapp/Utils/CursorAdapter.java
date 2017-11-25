package com.alifyz.inventoryapp.Utils;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alifyz.inventoryapp.R;

/**
 * Created by alifyzpires on 2017-11-21.
 */

public class CursorAdapter extends android.widget.CursorAdapter{

    public CursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_list, parent,false);
    }

    //TODO - Format and get the rest of the views in this method
    //TODO - Fix the price dislay
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView price = (TextView) view.findViewById(R.id.price);

        String nameFromDb = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        int priceFromDb = cursor.getInt(cursor.getColumnIndexOrThrow("price"));
        double priceInUS = priceFromDb / 100;

        name.setText(nameFromDb);
        price.setText(String.valueOf(priceInUS));

    }
}
