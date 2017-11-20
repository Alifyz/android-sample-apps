package com.alifyz.inventoryapp.Database;

import android.provider.BaseColumns;

/**
 * Created by Alifyz on 11/19/2017.
 */

public class ProductDb {

    private ProductDb() {}

    public static class ProductEntry implements BaseColumns {

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
