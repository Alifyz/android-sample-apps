package com.alifyz.inventoryapp.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.alifyz.inventoryapp.Database.ProductDb.ProductEntry;


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
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
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
                int quantity = contentValues.getAsInteger(ProductEntry.COLUMN_QUANTITY);
                int sales = contentValues.getAsInteger(ProductEntry.COLUMN_SALES);

                if (productName == null || suplierName == null || suplierContact == null ||
                        quantity < 0 || sales < 0) {
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

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        SQLiteDatabase database = mDatabase.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case PRODUCT_DB:
                return updateProduct(uri, contentValues, selection, selectionArgs);
            case PRODUCT_ID:
                selection = ProductEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateProduct(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Invalid URI " + uri);
        }


    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase database = mDatabase.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCT_DB:
                int rowsUpdated_DB = database.delete(ProductEntry.TABLE_NAME,selection,selectionArgs);
                if(rowsUpdated_DB != 0)
                    getContext().getContentResolver().notifyChange(uri, null);
                return rowsUpdated_DB;
            case PRODUCT_ID:
                selection = ProductEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                int rowsUpdated_ID = database.delete(ProductEntry.TABLE_NAME, selection, selectionArgs);
                if(rowsUpdated_ID != 0)
                    getContext().getContentResolver().notifyChange(uri, null);
                return rowsUpdated_ID;
            default:
                throw new IllegalArgumentException("Error trying to delete URI " + uri);
        }
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCT_DB:
                return ProductEntry.CONTENT_LIST_TYPE;
            case PRODUCT_ID:
                return ProductEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Invalid MIME type");
        }
    }

    private int updateProduct(Uri uri, ContentValues contentValues,
                              String selection, String[] selectionArgs) {

        SQLiteDatabase database = mDatabase.getWritableDatabase();

        if (contentValues.size() == 0) {
            return 0;
        }

        if (contentValues.containsKey(ProductEntry.COLUMN_NAME)) {
            String productName = contentValues.getAsString(ProductEntry.COLUMN_NAME);
            if (productName == null) {
                throw new IllegalArgumentException("Name can't be empty");
            }
        }

        if (contentValues.containsKey(ProductEntry.COLUMN_SUPLIER_NAME)) {
            String suplierName = contentValues.getAsString(ProductEntry.COLUMN_SUPLIER_NAME);
            if (suplierName == null) {
                throw new IllegalArgumentException("Suplier can't be empty");
            }
        }

        if (contentValues.containsKey(ProductEntry.COLUMN_SUPLIER_CONTACT)) {
            String suplierContact = contentValues.getAsString(ProductEntry.COLUMN_SUPLIER_CONTACT);
            if (suplierContact == null) {
                throw new IllegalArgumentException("Suplier contact can't be empty");
            }
        }

        if (contentValues.containsKey(ProductEntry.COLUMN_QUANTITY)) {
            int quantity = contentValues.getAsInteger(ProductEntry.COLUMN_QUANTITY);
            if (quantity < 0) {
                throw new IllegalArgumentException("quantity can't be negative");
            }
        }

        if (contentValues.containsKey(ProductEntry.COLUMN_SALES)) {
            int sales = contentValues.getAsInteger(ProductEntry.COLUMN_SALES);
            if (sales < 0) {
                throw new IllegalArgumentException("sales can't be negative");
            }
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return database.update(ProductEntry.TABLE_NAME, contentValues, selection, selectionArgs);
    }
}
