package com.alifyz.newsapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<News>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
    }

    @Override
    public Loader<News> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<News> loader, News news) {

    }

    @Override
    public void onLoaderReset(Loader<News> loader) {

    }
}
