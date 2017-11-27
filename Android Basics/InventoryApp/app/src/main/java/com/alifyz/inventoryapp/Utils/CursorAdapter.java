package com.alifyz.inventoryapp.Utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;

import com.alifyz.inventoryapp.ProductDetailsActivity;
import com.alifyz.inventoryapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alifyz.inventoryapp.Database.ProductDb.ProductEntry;

import java.util.Locale;

/**
 * Created by alifyzpires on 2017-11-21.
 */

public class CursorAdapter extends android.widget.CursorAdapter{

    private TextView mProductName;
    private TextView mProductPrice;
    private TextView mProductQuantity;
    private TextView mProductSales;
    private TextView mProductAvailable;
    private TextView mProductSupplier;
    private ImageView mProductImage;

    private String mNameFromDatabase;
    private double mPriceFromDatabase;
    private String mPriceNumberFromDatabase;
    private int mQuantityFromDatabase;
    private int mSalesFromDatabase;
    private String mSupplierFromDatabase;
    private String mImageStringURI;
    private Uri mImageUri;

    public CursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_list, parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        extractData(cursor);

        bindViews(view);

        setData(context);

        if(mImageUri != null) {
            mProductImage.setImageURI(Uri.parse(mImageStringURI));
        }

        if(mQuantityFromDatabase <= 0) {
            mProductAvailable.setText(context.getString(R.string.noStock));
        } else {
            mProductAvailable.setText(context.getString(R.string.inStock));
        }
    }

    private void bindViews(View view) {
         mProductName = (TextView) view.findViewById(R.id.name);
         mProductPrice = (TextView) view.findViewById(R.id.price);
         mProductQuantity = (TextView) view.findViewById(R.id.quantity);
         mProductSales = (TextView) view.findViewById(R.id.sales);
         mProductAvailable = (TextView) view.findViewById(R.id.in_stock);
         mProductSupplier = (TextView) view.findViewById(R.id.suplier);
         mProductImage = (ImageView) view.findViewById(R.id.profile_image);
    }

    private void extractData(Cursor cursor) {
         mNameFromDatabase = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME));
         mPriceFromDatabase = (cursor.getDouble(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRICE))) /100;
         mPriceNumberFromDatabase =  String.format(Locale.getDefault(),"%.2f", mPriceFromDatabase);
         mQuantityFromDatabase = cursor.getInt(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_QUANTITY));
         mSalesFromDatabase = ProductDetailsActivity.mCurrentSales;
         mSupplierFromDatabase = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_SUPLIER_NAME));
         mImageStringURI = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_IMAGE));
         mImageUri = Uri.parse(mImageStringURI);
    }

    private void setData(Context context) {
        mProductName.setText(mNameFromDatabase);
        mProductPrice.setText(context.getString(R.string.moneyTag));
        mProductPrice.append(mPriceNumberFromDatabase);
        mProductQuantity.setText(context.getString(R.string.qtdCode));
        mProductQuantity.append(String.valueOf(mQuantityFromDatabase));
        mProductSales.setText(String.valueOf(mSalesFromDatabase));
        mProductSales.append(" " + context.getString(R.string.salesInfo));
        mProductSupplier.setText(context.getString(R.string.supplier));
        mProductSupplier.append(" " + mSupplierFromDatabase);
    }

    private void BitmapOptimization() {

    }

}
