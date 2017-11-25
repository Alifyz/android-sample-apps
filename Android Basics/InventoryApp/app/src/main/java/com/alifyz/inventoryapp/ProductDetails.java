package com.alifyz.inventoryapp;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import com.alifyz.inventoryapp.Database.ProductDb.ProductEntry;

import org.w3c.dom.Text;

public class ProductDetails extends AppCompatActivity {

    private Uri productUri = null;
    private EditText mProductName;
    private EditText mProductPrice;
    private EditText mProductQtd;
    private EditText mProductSales;
    private TextView mProductAvailability;
    private EditText mProductSupplier;
    private EditText mProductSuppEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        mProductName = (EditText) findViewById(R.id.edit_name);
        mProductPrice = (EditText) findViewById(R.id.edit_price);
        mProductQtd = (EditText) findViewById(R.id.edit_quantity);
        mProductSales = (EditText) findViewById(R.id.edit_sales);
        mProductAvailability = (TextView) findViewById(R.id.available);
        mProductSupplier = (EditText) findViewById(R.id.edit_supplier_name);
        mProductSuppEmail = (EditText) findViewById(R.id.edit_contact);


        Intent getUpdateRequest = getIntent();
        productUri = getUpdateRequest.getData();

        if(productUri == null) {
            setTitle(getString(R.string.add_product));
        }
        else {
            setTitle(getString(R.string.edit_product));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                getContentResolver().insert(ProductEntry.CONTENT_URI, wrapData());
                finish();
                break;
            case R.id.action_delete:
                //TODO Close Current Activity
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private ContentValues wrapData() {

        String ProductName = mProductName.getText().toString();
        int ProductPrice = Integer.parseInt(mProductPrice.getText().toString());
        int ProductQtd = Integer.parseInt(mProductQtd.getText().toString());
        int ProductSales = Integer.parseInt(mProductSales.getText().toString());
        String ProductAvailability = mProductAvailability.getText().toString();
        String ProductSupplier = mProductSupplier.getText().toString();
        String ProductContact = mProductSuppEmail.getText().toString();

        ContentValues data = new ContentValues();
        data.put(ProductEntry.COLUMN_NAME, ProductName);
        data.put(ProductEntry.COLUMN_PRICE, ProductPrice);
        data.put(ProductEntry.COLUMN_QUANTITY, ProductQtd);
        data.put(ProductEntry.COLUMN_SALES, ProductSales);
        data.put(ProductEntry.COLUMN_SUPLIER_NAME, ProductSupplier);
        data.put(ProductEntry.COLUMN_SUPLIER_CONTACT, ProductContact);

        return data;
    }
}
