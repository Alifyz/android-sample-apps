package com.alifyz.inventoryapp.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.alifyz.inventoryapp.Database.ProductDb.ProductEntry;

import java.util.function.ToDoubleBiFunction;

/**
 * Created by Alifyz Pires on 2017-11-20.
 * Implementing the Class Content Providor where we will implement the CRUD Methods
 */

public class ProductProvider extends ContentProvider{

    private static ProductDbHelper mDatabase;
    private static final int PRODUCT_DB = 100;
    private static final int PRODUCT_ID = 101;
    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(ProductDb.CONTENT_AUTHORITY, ProductDb.PATH_PRODUCT, PRODUCT_DB);
        sUriMatcher.addURI(ProductDb.CONTENT_AUTHORITY, ProductDb.PATH_PRODUCT+"/#", PRODUCT_ID);
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
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(ProductEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Invalid URI, " + uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
