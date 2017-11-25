package com.alifyz.inventoryapp.Utils;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alifyz.inventoryapp.Database.ProductDb;
import com.alifyz.inventoryapp.Database.ProductDb.ProductEntry;
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


        TextView productName = (TextView) view.findViewById(R.id.name);
        TextView productPrice = (TextView) view.findViewById(R.id.price);


        String nameFromDb = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME));
        double priceFromDb = cursor.getDouble(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRICE));
        double price = priceFromDb / 100;


        productName.setText(nameFromDb);
        productPrice.setText(String.valueOf(price));


    }
}
