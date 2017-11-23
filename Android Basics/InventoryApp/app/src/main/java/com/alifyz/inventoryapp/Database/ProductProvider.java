package com.alifyz.inventoryapp.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alifyz.inventoryapp.Database.ProductDb.ProductEntry;
import com.alifyz.inventoryapp.R;

import static android.content.res.Resources.*;


/**
 * Created by Alifyz Pires on 2017-11-20.
 * Implementing the Class Content Providor where we will implement the CRUD Methods
 */

public class ProductProvider extends ContentProvider {

    private static String LOG_TAG = "Product Provider";
    private static ProductDbHelper mDatabase;
    private static final int PRODUCT_DB = 100;
    private static final int PRODUCT_ID = 101;
    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(ProductDb.CONTENT_AUTHORITY, ProductDb.PATH_PRODUCT, PRODUCT_DB);
        sUriMatcher.addURI(ProductDb.CONTENT_AUTHORITY, ProductDb.PATH_PRODUCT + "/#", PRODUCT_ID);
    }


    @Override
    public boolean onCreate() {
        mDatabase = new ProductDbHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        Cursor cursor = null;
        SQLiteDatabase database = mDatabase.getReadableDatabase();
        int match = sUriMatcher.match(uri);

        switch (match) {
            case PRODUCT_DB:
                cursor = database.query(ProductEntry.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder);
                break;
            case PRODUCT_ID:
                selection = ProductEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(ProductEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Invalid URI, " + uri);
        }

        return cursor;
    }


    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        SQLiteDatabase database = mDatabase.getWritableDatabase();
        long id;
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case PRODUCT_DB:

                String productName = contentValues.getAsString(ProductEntry.COLUMN_NAME);
                String suplierName = contentValues.getAsString(ProductEntry.COLUMN_SUPLIER_NAME);
                String suplierContact = contentValues.getAsString(ProductEntry.COLUMN_SUPLIER_CONTACT);

                if (productName == null || suplierName == null || suplierContact == null) {
                    throw new IllegalArgumentException("Invalid data being inserted into the database");
                }

                id = database.insert(ProductEntry.TABLE_NAME, null, contentValues);
                if (id == -1) {
                    Log.e(LOG_TAG, "Data inserted correctly");
                    return null;
                } else {
                    Log.i(LOG_TAG, "Error trying to insert the data");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid URI for " + uri);
        }

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}
