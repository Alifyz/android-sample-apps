package com.alifyz.csbooking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
 * This Class is intended to provide helper methods to start a Network Request
 * and then parse the results
 */

public class BookUtils {

    private static String jsonTitle;
    private static String jsonAuthor;
    private static String jsonDescription;
    private static String jsonImageURL;

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

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readInputStream(inputStream);
            }

        } catch (IOException e) {
            Log.e("BookUtils", "Error trying to Open URL Connection");
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
            if (inputStream != null)
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
        ArrayList<Book> books = new ArrayList<>();

        //Iterate Over the JSON File and Parse the Objects as Individuals.
        try {
            JSONObject jsonRoot = new JSONObject(rawJSON);
            JSONArray jsonItems = jsonRoot.getJSONArray("items");
            for (int i = 0; i < jsonItems.length(); i++) {

                JSONObject jsonBooks = jsonItems.getJSONObject(i);
                JSONObject jsonVolumeInfo = jsonBooks.getJSONObject("volumeInfo");
                JSONObject jsonBookImage = jsonVolumeInfo.getJSONObject("imageLinks");
                JSONObject jsonBookDescription = jsonBooks.getJSONObject("searchInfo");

                jsonTitle = jsonVolumeInfo.getString("title");

                if (jsonVolumeInfo.has("authors")) {
                    jsonAuthor = jsonVolumeInfo.getString("authors");
                    jsonAuthor = jsonAuthor.replaceAll("[^A-Za-z0-9 ]", "");
                } else {
                    jsonAuthor = jsonVolumeInfo.getString("publisher");
                    jsonAuthor = jsonAuthor.replaceAll("[^A-Za-z0-9 ]", "");
                }
                jsonDescription = jsonBookDescription.getString("textSnippet");
                jsonImageURL = jsonBookImage.getString("thumbnail");

                Bitmap currentBookCover = loadBitmap(jsonImageURL);
                Book currentBook = new Book(currentBookCover, jsonTitle, jsonAuthor, jsonDescription);
                books.add(currentBook);
            }
        } catch (JSONException e) {
            Log.e("BookUtils", "Error parsing the JSON from the Server");
        }
        return books;
    }

    /*
     * Helper method to decode the Image UrlStream and return a Bitmap
     */
    public static Bitmap loadBitmap(String url) {

        Bitmap bookCover = null;
        URL imageURL = null;
        try {
            imageURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            bookCover = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookCover;
    }
}
