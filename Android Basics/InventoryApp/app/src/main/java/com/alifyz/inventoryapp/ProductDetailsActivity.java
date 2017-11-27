package com.alifyz.inventoryapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alifyz.inventoryapp.Database.ProductDb.ProductEntry;
import com.alifyz.inventoryapp.Utils.CursorUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ProductDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private EditText mProductName;
    private EditText mProductPrice;
    private EditText mProductQtd;
    private EditText mProductSupplier;
    private EditText mProductSuppEmail;
    private Button mNewOrder;
    private Uri currentUri;
    private ImageView mProductImage;

    private Boolean mProductHasChanged = false;
    private Button addQtd;
    private Button remQtd;
    private Button mSalesBtn;

    public static int mCurrentSales = 0;
    private int mCurrentQtd = 0;
    private Uri mImageResourceUri;
    private TextView mProductImageText;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        Intent getUpdateRequest = getIntent();
        currentUri = getUpdateRequest.getData();

        bindViews();
        setViewsListeners();

        if (currentUri == null) {
            setTitle(getString(R.string.add_product));
            mNewOrder.setVisibility(View.GONE);
            invalidateOptionsMenu();
        } else {
            setTitle(getString(R.string.edit_product));
            getLoaderManager().initLoader(0, null, this);
            mNewOrder.setVisibility(View.VISIBLE);
        }

        addQtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addQuantity();
            }
        });

        remQtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeQuantity();
            }
        });

        mSalesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellProduct();
            }
        });

        mProductImageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grabPhoto();
            }
        });

        mNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                composeEmail();
            }
        });

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
                if (currentUri == null)
                    insertProduct();
                else
                    updateProduct();
                finish();
                break;
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                break;
            case android.R.id.home:
                if (!mProductHasChanged) {
                    NavUtils.navigateUpFromSameTask(ProductDetailsActivity.this);
                    return true;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NavUtils.navigateUpFromSameTask(ProductDetailsActivity.this);
                            }
                        };
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Helper Method to Grab the Data from the EditText and Put into the SQLite Database
    private ContentValues wrapData() {

        ContentValues data = new ContentValues();
        String ProductName = "";
        double ProductPrice;
        int ProductQtd;
        int ProductSales = 0;
        String ProductSupplier = "";
        String ProductContact = "";
        String imageUri = null;

        try {
            ProductName = mProductName.getText().toString();
            ProductPrice = Double.parseDouble(mProductPrice.getText().toString());
            ProductQtd = Integer.parseInt(mProductQtd.getText().toString());
            ProductSupplier = mProductSupplier.getText().toString();
            ProductContact = mProductSuppEmail.getText().toString();
            imageUri = getImageURI();

        } catch (NumberFormatException e) {
            Log.e("ProductDetailsActivity", "Error parsing the Price or Qtd or Sales");
            ProductPrice = 0;
            ProductQtd = 0;
            ProductSales = 0;
        }
        if (isEntryValid()) {
            showInvalidToast();
            finish();
            return null;
        } else {
            data.put(ProductEntry.COLUMN_NAME, ProductName);
            data.put(ProductEntry.COLUMN_PRICE, ProductPrice);
            data.put(ProductEntry.COLUMN_QUANTITY, ProductQtd);
            data.put(ProductEntry.COLUMN_SALES, ProductSales);
            data.put(ProductEntry.COLUMN_SUPLIER_NAME, ProductSupplier);
            data.put(ProductEntry.COLUMN_SUPLIER_CONTACT, ProductContact);
            data.put(ProductEntry.COLUMN_IMAGE, imageUri);
        }
        return data;
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

        return new CursorLoader(this, currentUri,
                projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor.moveToFirst()) {

            int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_NAME);
            int qtdColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_QUANTITY);
            int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRICE);
            int suppColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_SUPLIER_NAME);
            int emailColumIndex = cursor.getColumnIndex(ProductEntry.COLUMN_SUPLIER_CONTACT);
            int imageUriColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_IMAGE);

            String imageStringURL = cursor.getString(imageUriColumnIndex);

            Log.d("Image current Uri", imageStringURL);

            String productName = cursor.getString(nameColumnIndex);
            int productQuantity = cursor.getInt(qtdColumnIndex);
            double productPrice = cursor.getDouble(priceColumnIndex);
            String productSupplier = cursor.getString(suppColumnIndex);
            String productContact = cursor.getString(emailColumIndex);

            mProductName.setText(productName);
            mProductQtd.setText(String.valueOf(productQuantity));
            mProductPrice.setText(String.valueOf(productPrice));
            mProductSupplier.setText(String.valueOf(productSupplier));
            mProductSuppEmail.setText(productContact);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {}

    private void insertProduct() {
        if (wrapData() != null) {
            getContentResolver().insert(ProductEntry.CONTENT_URI, wrapData());
        }
    }

    private void updateProduct() {
        if (wrapData() != null) {
            getContentResolver().update(currentUri, wrapData(), null, null);
        }
    }

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mProductHasChanged = true;
            return false;
        }
    };

    //Code Extracted from Udacity Android Basics - Lesson Content Loaders
    private void showUnsavedChangesDialog( DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //Code Extracted from Udacity Android Basics - Lesson Content Loaders
    @Override
    public void onBackPressed() {
        if (!mProductHasChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (currentUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    //Code Extracted from Udacity Android Basics - Lesson Content Loaders
    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
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
        getContentResolver().delete(currentUri, null, null);
        Toast.makeText(this, getString(R.string.productremoved), Toast.LENGTH_SHORT).show();
        finish();
    }

    private void grabPhoto() {
        Intent getImageUri = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        getImageUri.addCategory(Intent.CATEGORY_OPENABLE);
        getImageUri.setType("image/*");
        startActivityForResult(getImageUri, REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                mImageResourceUri = resultData.getData();
                Bitmap extractedImageUtils = CursorUtils.getBitmapFromUri(mImageResourceUri, this, mProductImage);
                if (extractedImageUtils != null) {
                    mProductImage.setImageBitmap(extractedImageUtils);
                }
            }
        }
    }

    private String getImageURI() {
        if (mImageResourceUri != null) {
            return mImageResourceUri.toString();
        } else {
            return null;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void composeEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "New Order: " + mProductName.getText().toString());
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {mProductSuppEmail.getText().toString()});

        String bodyMessage = "Dear, " + mProductSupplier.getText().toString() + "\n"
                + "Our Store would like to order more of " + mProductName.getText().toString();
        intent.putExtra(Intent.EXTRA_TEXT, bodyMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void addQuantity() {
        if (!mProductQtd.getText().toString().isEmpty()) {
            mCurrentQtd = Integer.parseInt(mProductQtd.getText().toString());
            mCurrentQtd++;
            mProductQtd.setText(String.valueOf(mCurrentQtd));
            updateQuantity();
        }
    }

    private void removeQuantity() {
        if (!mProductQtd.getText().toString().isEmpty()) {
            mCurrentQtd = Integer.parseInt(mProductQtd.getText().toString());
            mCurrentQtd--;
            mProductQtd.setText(String.valueOf(mCurrentQtd));
            if (mCurrentQtd <= 0) {
                mCurrentQtd = 0;
                mProductQtd.setText(String.valueOf(mCurrentQtd));
            }
            updateQuantity();
        }
    }

    private void sellProduct() {
        if (!mProductQtd.getText().toString().isEmpty()) {
            mCurrentQtd = Integer.parseInt(mProductQtd.getText().toString());
            if (mCurrentQtd > 0) {
                mCurrentSales++;
                mCurrentQtd--;
                mProductQtd.setText(String.valueOf(mCurrentQtd));
                ContentValues salesData = new ContentValues();
                salesData.put(ProductEntry.COLUMN_SALES, mCurrentSales);
                salesData.put(ProductEntry.COLUMN_QUANTITY, mCurrentQtd);
                getContentResolver().update(currentUri, salesData, null, null);
            }
        }
    }

    private void updateQuantity() {
        ContentValues data = new ContentValues();
        data.put(ProductEntry.COLUMN_QUANTITY, mCurrentQtd);
        getContentResolver().update(currentUri, data, null, null);
    }

    private void bindViews() {
        mProductName = (EditText) findViewById(R.id.edit_name);
        mProductPrice = (EditText) findViewById(R.id.edit_price);
        mProductQtd = (EditText) findViewById(R.id.edit_quantity);
        mProductSupplier = (EditText) findViewById(R.id.edit_supplier_name);
        mProductSuppEmail = (EditText) findViewById(R.id.edit_contact);
        mNewOrder = (Button) findViewById(R.id.new_order);
        addQtd = (Button) findViewById(R.id.add_qtd);
        remQtd = (Button) findViewById(R.id.rem_qtd);
        mSalesBtn = (Button) findViewById(R.id.sell_item);
        mProductImage = (ImageView) findViewById(R.id.product_image);
        mProductImage.setImageResource(R.drawable.default_photo);
        mProductImageText = (TextView) findViewById(R.id.picture_text);
    }

    private void setViewsListeners() {
        mProductName.setOnTouchListener(mTouchListener);
        mProductPrice.setOnTouchListener(mTouchListener);
        mProductQtd.setOnTouchListener(mTouchListener);
        mProductSupplier.setOnTouchListener(mTouchListener);
        mProductSuppEmail.setOnTouchListener(mTouchListener);
    }

    private Boolean isEntryValid() {
        if(mProductName.getText().toString().isEmpty()  || mProductSupplier.getText().toString().isEmpty()
            || mProductSuppEmail.getText().toString().isEmpty()) {
                return true;
        } else {
            return false;
        }
    }

    private void showInvalidToast() {
        Toast.makeText(ProductDetailsActivity.this, getString(R.string.invalidinput),
                Toast.LENGTH_LONG).show();
    }
}
