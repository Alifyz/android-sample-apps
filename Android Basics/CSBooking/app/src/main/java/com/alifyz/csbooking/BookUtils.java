package com.alifyz.csbooking;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Alifyz on 11/7/2017.
 */

public class BookUtils {

    private String mURL;
    private int mResults;

    public BookUtils(String mURL, int mResults) {
        this.mURL = mURL;
        this.mResults = mResults;
    }

    public BookUtils() {
    }


    public URL parseURL(String httpURL) {
        return null;
    }

    public String makeHttpRequest(URL httpURL) {
        return null;
    }

    public ArrayList<Book> extractJSON(String rawJSON) {
        return null;
    }

}
