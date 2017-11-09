package com.alifyz.csbooking;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

public class BookResult extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<List<Book>>{

    private BookAdapter adapter;
    private ListView bookListView;
    private ProgressBar progressBar;
    public static String keywords = null;
    public static String finalURL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_root);

        bookListView = (ListView)findViewById(R.id.listView_root);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        Intent intentReceiver = getIntent();
        keywords = intentReceiver.getStringExtra("Keyword");
        if(keywords.contains(" ")) {
            keywords.replace(" ", "%20");
        }

        finalURL = "https://www.googleapis.com/books/v1/volumes?q=" + keywords + "&maxResults=10";
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
    }
}
