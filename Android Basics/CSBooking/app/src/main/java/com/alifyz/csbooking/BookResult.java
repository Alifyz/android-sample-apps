package com.alifyz.csbooking;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class BookResult extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<List<Book>>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_template);

        //Extract the Keyword from the HomeActivity
        Intent intentReceiver = getIntent();
        String keywords = intentReceiver.getStringExtra("Keyword");

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {

    }
}
