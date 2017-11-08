package com.alifyz.csbooking;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
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


    public static URL parseURL(String httpURL) {
        URL tempURL = null;
        try {
            tempURL = new URL(httpURL);
        } catch (MalformedURLException e) {
            Log.e("BookLoader", "Malformed URL");
        }
        return tempURL;
    }

    public static String makeHttpRequest(URL httpURL) {
        return null;
    }

    public static String readInputStream(InputStream inputStream) throws IOException {
        StringBuilder rawJSON = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputReader);
            String line = reader.readLine();
            while (line != null) {
                rawJSON.append(line);
                line = reader.readLine();
            }
        }
        return rawJSON.toString();
    }

    public ArrayList<Book> extractJSON(String rawJSON) {
        return null;
    }



}
