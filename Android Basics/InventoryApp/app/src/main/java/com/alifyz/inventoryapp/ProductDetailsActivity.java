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
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alifyz.inventoryapp.Database.ProductDb.ProductEntry;
import com.alifyz.inventoryapp.Utils.CursorUtils;

public class ProductDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private EditText mProductName;
    private EditText mProductPrice;
    private EditText mProductQuantity;
    private EditText mProductSupplier;
    private EditText mProductSuppEmail;
    private Button mNewOrder;
    private Uri currentUri;
    private ImageView mProductImage;
    private Boolean mProductHasChanged = false;
    private Button addQuantity;
    private Button removeQuantity;
    private Button mSalesBtn;
    private TextView mProductImageText;
    private  String imageStringURL;
    private Uri mImageResourceUri;
    private static final int REQUEST_CODE = 1;
    public static int mCurrentSales = 0;
    private int mCurrentQuantity = 0;

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

        addQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addQuantity();
            }
        });

        removeQuantity.setOnClickListener(new View.OnClickListener() {
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
            ProductQtd = Integer.parseInt(mProductQuantity.getText().toString());
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

            imageStringURL = cursor.getString(imageUriColumnIndex);
            String productName = cursor.getString(nameColumnIndex);
            int productQuantity = cursor.getInt(qtdColumnIndex);
            double productPrice = cursor.getDouble(priceColumnIndex);
            String productSupplier = cursor.getString(suppColumnIndex);
            String productContact = cursor.getString(emailColumIndex);

            mProductName.setText(productName);
            mProductQuantity.setText(String.valueOf(productQuantity));
            mProductPrice.setText(String.valueOf(productPrice));
            mProductSupplier.setText(String.valueOf(productSupplier));
            mProductSuppEmail.setText(productContact);
            mProductImage.setImageURI(Uri.parse(imageStringURL));
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
        } else if(imageStringURL == null) {
            return CursorUtils.getDefaultUri(ProductDetailsActivity.this).toString();
        } else if (!imageStringURL.isEmpty()){
            return Uri.parse(imageStringURL).toString();
        } else {
            return CursorUtils.getDefaultUri(ProductDetailsActivity.this).toString();
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
        if (!mProductQuantity.getText().toString().isEmpty()) {
            mCurrentQuantity = Integer.parseInt(mProductQuantity.getText().toString());
            mCurrentQuantity++;
            mProductQuantity.setText(String.valueOf(mCurrentQuantity));
            updateQuantity();
        }
    }

    private void removeQuantity() {
        if (!mProductQuantity.getText().toString().isEmpty()) {
            mCurrentQuantity = Integer.parseInt(mProductQuantity.getText().toString());
            mCurrentQuantity--;
            mProductQuantity.setText(String.valueOf(mCurrentQuantity));
            if (mCurrentQuantity <= 0) {
                mCurrentQuantity = 0;
                mProductQuantity.setText(String.valueOf(mCurrentQuantity));
            }
            updateQuantity();
        }
    }

    private void sellProduct() {
        if (!mProductQuantity.getText().toString().isEmpty()) {
            mCurrentQuantity = Integer.parseInt(mProductQuantity.getText().toString());
            if (mCurrentQuantity > 0) {
                mCurrentSales++;
                mCurrentQuantity--;
                mProductQuantity.setText(String.valueOf(mCurrentQuantity));
                ContentValues salesData = new ContentValues();
                salesData.put(ProductEntry.COLUMN_SALES, mCurrentSales);
                salesData.put(ProductEntry.COLUMN_QUANTITY, mCurrentQuantity);
                if(currentUri != null) {
                    getContentResolver().update(currentUri, salesData, null, null);
                }
            }
        }
    }

    private void updateQuantity() {
        ContentValues data = new ContentValues();
        data.put(ProductEntry.COLUMN_QUANTITY, mCurrentQuantity);
        if(currentUri != null) {
            getContentResolver().update(currentUri, data, null, null);
        }
    }

    private void bindViews() {
        mProductName = (EditText) findViewById(R.id.edit_name);
        mProductPrice = (EditText) findViewById(R.id.edit_price);
        mProductQuantity = (EditText) findViewById(R.id.edit_quantity);
        mProductSupplier = (EditText) findViewById(R.id.edit_supplier_name);
        mProductSuppEmail = (EditText) findViewById(R.id.edit_contact);
        mNewOrder = (Button) findViewById(R.id.new_order);
        addQuantity = (Button) findViewById(R.id.add_qtd);
        removeQuantity = (Button) findViewById(R.id.rem_qtd);
        mSalesBtn = (Button) findViewById(R.id.sell_item);
        mProductImage = (ImageView) findViewById(R.id.product_image);
        mProductImage.setImageResource(R.drawable.default_photo);
        mProductImageText = (TextView) findViewById(R.id.picture_text);
    }

    private void setViewsListeners() {
        mProductName.setOnTouchListener(mTouchListener);
        mProductPrice.setOnTouchListener(mTouchListener);
        mProductQuantity.setOnTouchListener(mTouchListener);
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
