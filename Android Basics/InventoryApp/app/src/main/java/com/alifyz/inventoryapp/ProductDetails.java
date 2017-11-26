package com.alifyz.inventoryapp;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alifyz.inventoryapp.Database.ProductDb.ProductEntry;

public class ProductDetails extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor>{

    private EditText mProductName;
    private EditText mProductPrice;
    private EditText mProductQtd;
    private EditText mProductSupplier;
    private EditText mProductSuppEmail;
    private Button mNewOrder;
    private Uri currentUri;
    private Cursor currentData = null;
    private Boolean mProductHasChanged = false;
    private Button addQtd;
    private Button remQtd;
    private Button mSalesBtn;
    public static int mCurrentSales = 0;
    private int mCurrentQtd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        mProductName = (EditText) findViewById(R.id.edit_name);
        mProductPrice = (EditText) findViewById(R.id.edit_price);
        mProductQtd = (EditText) findViewById(R.id.edit_quantity);
        mProductSupplier = (EditText) findViewById(R.id.edit_supplier_name);
        mProductSuppEmail = (EditText) findViewById(R.id.edit_contact);
        mNewOrder = (Button) findViewById(R.id.new_order);
        addQtd = (Button) findViewById(R.id.add_qtd);
        remQtd = (Button) findViewById(R.id.rem_qtd);
        mSalesBtn = (Button) findViewById(R.id.sell_item);

        addQtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentQtd = Integer.parseInt(mProductQtd.getText().toString());
                mCurrentQtd++;
                mProductQtd.setText(String.valueOf(mCurrentQtd));
                ContentValues data = new ContentValues();
                data.put(ProductEntry.COLUMN_QUANTITY, mCurrentQtd);
                getContentResolver().update(currentUri, data, null, null);
            }
        });

        remQtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentQtd = Integer.parseInt(mProductQtd.getText().toString());
                mCurrentQtd--;
                mProductQtd.setText(String.valueOf(mCurrentQtd));
                if(mCurrentQtd <= 0) {
                    mCurrentQtd = 0;
                    mProductQtd.setText(String.valueOf(mCurrentQtd));

                }
                ContentValues data = new ContentValues();
                data.put(ProductEntry.COLUMN_QUANTITY, mCurrentQtd);
                getContentResolver().update(currentUri, data, null, null);
            }
        });

        mSalesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentQtd = Integer.parseInt(mProductQtd.getText().toString());
                if(mCurrentQtd > 0) {
                    mCurrentSales++;
                    mCurrentQtd--;
                    mProductQtd.setText(String.valueOf(mCurrentQtd));
                    ContentValues salesData = new ContentValues();
                    salesData.put(ProductEntry.COLUMN_SALES, mCurrentSales);
                    salesData.put(ProductEntry.COLUMN_QUANTITY, mCurrentQtd);
                    getContentResolver().update(currentUri, salesData, null, null);
                }
            }
        });


        mProductName.setOnTouchListener(mTouchListener);
        mProductPrice.setOnTouchListener(mTouchListener);
        mProductQtd.setOnTouchListener(mTouchListener);
        mProductSupplier.setOnTouchListener(mTouchListener);
        mProductSuppEmail.setOnTouchListener(mTouchListener);

        Intent getUpdateRequest = getIntent();
        currentUri = getUpdateRequest.getData();

        if(currentUri == null) {
            setTitle(getString(R.string.add_product));
            mNewOrder.setVisibility(View.GONE);
            invalidateOptionsMenu();
        }
        else {
            setTitle(getString(R.string.edit_product));
            getLoaderManager().initLoader(0, null, this);
            mNewOrder.setVisibility(View.VISIBLE);
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
                if(currentUri == null)
                    insertProduct();
                else
                    updateProduct();
                finish();
                break;
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                //finish();
                break;
            case android.R.id.home:
                // Se o pet não mudou, continua navegando pra cima para a activity pai
                // que é a {@link CatalogActivity}.
                if (!mProductHasChanged) {
                    NavUtils.navigateUpFromSameTask(ProductDetails.this);
                    return true;
                }

                // Caso contrário se há alterações não salvas, configura um dialog para alertar o usuário.
                // Cria um click listener para lidar com o usuário confirmando que
                // mudanças devem ser descartadas.
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Usuário clidou no botão "Discard", e navegou para a activity pai.
                                NavUtils.navigateUpFromSameTask(ProductDetails.this);
                            }
                        };

                // Mostra um dialog que notifica o usuário que eles tem alterações não salvas
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private ContentValues wrapData() {

        ContentValues data = new ContentValues();

        String ProductName = "";
        double ProductPrice;
        int ProductQtd;
        int ProductSales = 0;
        String ProductSupplier = "";
        String ProductContact  = "";

        try {

             ProductName = mProductName.getText().toString();
             ProductPrice = Double.parseDouble(mProductPrice.getText().toString());
             ProductQtd = Integer.parseInt(mProductQtd.getText().toString());
             ProductSupplier = mProductSupplier.getText().toString();
             ProductContact = mProductSuppEmail.getText().toString();

        } catch (NumberFormatException e) {
            Log.e("ProductDetails", "Error parsing the Price or Qtd or Sales");
            ProductPrice = 0;
            ProductQtd = 0;
            ProductSales = 0;
        }

        if(mProductName.getText().toString().isEmpty()
                || mProductSupplier.getText().toString().isEmpty()
                || mProductSuppEmail.getText().toString().isEmpty()) {
                    Toast.makeText(ProductDetails.this, getString(R.string.invalidinput), Toast.LENGTH_LONG).show();
                    finish();
                    return null;
        } else {
            data.put(ProductEntry.COLUMN_NAME, ProductName);
            data.put(ProductEntry.COLUMN_PRICE, ProductPrice);
            data.put(ProductEntry.COLUMN_QUANTITY, ProductQtd);
            data.put(ProductEntry.COLUMN_SALES, ProductSales);
            data.put(ProductEntry.COLUMN_SUPLIER_NAME, ProductSupplier);
            data.put(ProductEntry.COLUMN_SUPLIER_CONTACT, ProductContact);
            return data;

        }
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
        if(cursor.moveToFirst()) {

            int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_NAME);
            int qtdColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_QUANTITY);
            int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRICE);
            int salesColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_SALES);
            int suppColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_SUPLIER_NAME);
            int emailColumIndex = cursor.getColumnIndex(ProductEntry.COLUMN_SUPLIER_CONTACT);
            int imageColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_IMAGE);

            String productName = cursor.getString(nameColumnIndex);
            int productQuantity= cursor.getInt(qtdColumnIndex);
            double productPrice = cursor.getDouble(priceColumnIndex);
            int productSales = cursor.getInt(salesColumnIndex);
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
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void insertProduct() {
        if(wrapData() != null) {
            getContentResolver().insert(ProductEntry.CONTENT_URI, wrapData());
        }
    }

    private void updateProduct() {
        if(wrapData() != null) {
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

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Cria um AlertDialog.Builder e seta a mensagem, e click listeners
        // para os botões positivos e negativos do dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicou no botão "Continuar editando", então feche o dialog
                // e continue editando o pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Cria e mostra o AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        // Se o pet não mudou, continue lidando com clique do botão back
        if (!mProductHasChanged) {
            super.onBackPressed();
            return;
        }

        // Caso contrário se há alterações não salvas, configure um dialog para alertar o usuário.
        // Crie um click listener para lidar com o usuário confirmando que mudanças devem ser descartadas.
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicou no botão "Discard", fecha a activity atual.
                        finish();
                    }
                };

        // Mostra dialog dizendo que há mudanças não salvas
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new pet, hide the "Delete" menu item.
        if (currentUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.deleteDialog, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the pet.
                deleteProduct();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Perform the deletion of the pet in the database.
     */
    private void deleteProduct() {
        getContentResolver().delete(currentUri, null, null);
        Toast.makeText(this, getString(R.string.productremoved), Toast.LENGTH_SHORT).show();
        finish();
    }


}
