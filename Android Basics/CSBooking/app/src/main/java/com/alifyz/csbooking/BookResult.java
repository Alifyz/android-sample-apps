package com.alifyz.csbooking;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class BookResult extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private BookAdapter adapter;
    private ListView bookListView;
    private ProgressBar progressBar;
    private TextView emptyView;
    public static String keywords = null;
    public static String encodedKeywords = null;
    public static String finalURL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_root);

        bookListView = (ListView) findViewById(R.id.listView_root);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        emptyView = (TextView) findViewById(R.id.empty_view);

        Intent intentReceiver = getIntent();
        keywords = intentReceiver.getStringExtra("Keyword");

        encodeURL();

        if (isNetworkAvailable() == false) {
            emptyView.setText("There is no Internet Connection");
        }

        getLoaderManager().initLoader(0, null, this).forceLoad();

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(BookResult.this);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {

        adapter = new BookAdapter(getApplicationContext(), books);
        bookListView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
        if (adapter.getCount() == 0 && isNetworkAvailable() == true) {
            emptyView.setText("Sorry, I couldn't find anything. Try again please!");
        }
    }

    private void encodeURL() {
        if (keywords.contains(" ")) {
            encodedKeywords = keywords.replaceAll(" ", "%20");
            finalURL = "https://www.googleapis.com/books/v1/volumes?q=" + encodedKeywords + "&maxResults=10";
        } else {
            finalURL = "https://www.googleapis.com/books/v1/volumes?q=" + keywords + "&maxResults=10";
        }
    }

    //This function was provided thanks to the User Alexandre Jasmin, from StackOverflow
    //https://goo.gl/GHA7wt
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
