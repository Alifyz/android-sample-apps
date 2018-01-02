package com.alifyz.csbooking;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Alifyz on 11/7/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    public static String rawJSON = "";

    public BookLoader(Context context) {
        super(context);
    }

    @Override
    public List<Book> loadInBackground() {
        URL parsedURL = BookUtils.parseURL(BookResult.finalURL);
        try {
            rawJSON = BookUtils.makeHttpRequest(parsedURL);
        } catch (IOException e) {
            return null;
        }
        List<Book> books = BookUtils.extractBooks(rawJSON);
        return books;
    }
}
