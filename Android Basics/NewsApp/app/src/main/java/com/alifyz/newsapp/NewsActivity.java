package com.alifyz.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{

    private ListView listView;
    private ArrayAdapter<News> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        listView = (ListView) findViewById(R.id.listView);

        //getLoaderManager().initLoader(0, null, this).forceLoad();
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(NewsActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        adapter = new NewsAdapter(getApplicationContext(), news);
        listView.setAdapter(adapter);
    }
    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

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
