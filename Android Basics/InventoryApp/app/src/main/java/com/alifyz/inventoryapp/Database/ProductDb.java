package com.alifyz.inventoryapp.Database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Alifyz on 11/19/2017.
 * Schema Class for Grouping the Structure of the Database
 * Contract Class for the Content Provider and DbHelper
 */

public class ProductDb {

    public static final String CONTENT_AUTHORITY = "com.alifyz.inventoryapp.product";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PRODUCT = "products";

    private ProductDb() {}

    public static class ProductEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCT);

        public static final String TABLE_NAME = "products";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_SALES = "sales";
        public static final String COLUMN_SUPLIER_NAME = "suplier";
        public static final String COLUMN_SUPLIER_CONTACT = "contact";

    }
}
