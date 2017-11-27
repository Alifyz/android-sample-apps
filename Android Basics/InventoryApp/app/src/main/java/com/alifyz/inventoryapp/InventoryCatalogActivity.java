package com.alifyz.inventoryapp;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.alifyz.inventoryapp.Database.ProductDb.ProductEntry;
import com.alifyz.inventoryapp.Utils.CursorAdapter;

public class InventoryCatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private final int LOADER_ID = 0;
    private TextView mEmptyText;
    private ListView mListView;
    private CursorAdapter mCursorAdapter;
    private FloatingActionButton mActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_catalog);

        mEmptyText = (TextView) findViewById(R.id.empty);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setEmptyView(mEmptyText);
        mActionButton = (FloatingActionButton) findViewById(R.id.fab);

        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ProductDetails = new Intent(InventoryCatalogActivity.this, ProductDetailsActivity.class);
                startActivity(ProductDetails);
            }
        });

        mCursorAdapter = new CursorAdapter(this, null);
        mListView.setAdapter(mCursorAdapter);

        getLoaderManager().initLoader(LOADER_ID, null, this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent updateProduct = new Intent(InventoryCatalogActivity.this, ProductDetailsActivity.class);
                Uri getCurrentProduct = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, id);
                updateProduct.setData(getCurrentProduct);
                startActivity(updateProduct);
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_NAME,
                ProductEntry.COLUMN_PRICE,
                ProductEntry.COLUMN_QUANTITY,
                ProductEntry.COLUMN_SALES,
                ProductEntry.COLUMN_IMAGE,
                ProductEntry.COLUMN_SUPLIER_NAME,
                ProductEntry.COLUMN_SUPLIER_CONTACT
        };

        return new CursorLoader(this, ProductEntry.CONTENT_URI,
                projection, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inventory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all:
                showDeleteConfirmationDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Code Based on the Udacity - Android Basics Content Provider Lessons
    private void showDeleteConfirmationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_all);
        builder.setPositiveButton(R.string.deleteDialog, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteProduct();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteProduct() {
        getContentResolver().delete(ProductEntry.CONTENT_URI,  null,null);
    }
}
