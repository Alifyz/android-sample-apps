package com.alifyz.inventoryapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alifyz on 11/19/2017.
 * Contract Class for the General Database
 */

public class ProductDbHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db";


    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_TABLE = "CREATE TABLE " + ProductDb.ProductEntry.TABLE_NAME + " ("
                + ProductDb.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ProductDb.ProductEntry.COLUMN_NAME +  " TEXT NOT NULL, "
                + ProductDb.ProductEntry.COLUMN_PRICE + " INTEGER DEFAULT 0, "
                + ProductDb.ProductEntry.COLUMN_IMAGE + " TEXT NOT NULL, "
                + ProductDb.ProductEntry.COLUMN_QUANTITY + " INTEGER DEFAULT 0, "
                + ProductDb.ProductEntry.COLUMN_SALES +    " INTEGER DEFAULT 0, "
                + ProductDb.ProductEntry.COLUMN_SUPLIER_NAME + " TEXT, "
                + ProductDb.ProductEntry.COLUMN_SUPLIER_CONTACT + " TEXT);";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TODO Implement the Logic of Upgrading the DB
    }

}
