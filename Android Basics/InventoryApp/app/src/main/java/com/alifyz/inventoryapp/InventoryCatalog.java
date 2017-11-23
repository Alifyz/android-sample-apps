package com.alifyz.inventoryapp;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.alifyz.inventoryapp.Database.ProductDb.ProductEntry;

public class InventoryCatalog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_catalog);
        insertTest();

    }

    //Method used to test the insert implementation.
    //TODO Delete this mehod once it becomes useless
    private void insertTest() {
        ContentValues content = new ContentValues();

        content.put(ProductEntry.COLUMN_NAME, "PHONE");
        content.put(ProductEntry.COLUMN_PRICE, 100);
        content.put(ProductEntry.COLUMN_QUANTITY,10);
        content.put(ProductEntry.COLUMN_SALES,70);
        content.put(ProductEntry.COLUMN_IMAGE,"test");
        content.put(ProductEntry.COLUMN_SUPLIER_NAME, "Kabum");
        content.put(ProductEntry.COLUMN_SUPLIER_CONTACT,"asdasd");

        Uri uri = getContentResolver().insert(ProductEntry.CONTENT_URI, content);
        Log.i("Inventory", uri.toString());

    }
}
