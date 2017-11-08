package com.alifyz.csbooking;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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

    public static String makeHttpRequest(URL httpURL) throws IOException {

        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {

            urlConnection = (HttpURLConnection) httpURL.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readInputStream(inputStream);
            }

        } catch (IOException e) {
            Log.e("BookUtils", "Error trying to Open URL Connection");
        }

        finally {
            if(urlConnection != null)
                urlConnection.disconnect();
            if(inputStream != null)
                inputStream.close();
        }

        return jsonResponse;
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

    public static ArrayList<Book> extractBooks(String rawJSON) {

        ArrayList<Book> books = new ArrayList<Book>();


        return null;
    }



}
