package com.alifyz.newsapp;
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
 * Created by Alifyz on 11/10/2017.
 */

public class NewsUtils {

    private static String jsonTitle;
    private static String jsonLink;
    private static String jsonDatePublication;
    private static String jsonAuthor;
    private static String jsonCategory;

    private static String API_URL =
            "http://content.guardianapis.com/search?show-tags=contributor&q=brazil%20corruption&api-key=67774176-cf6c-432e-81a7-91a9e253c61f";

    public NewsUtils() {
    }

    public static URL parseURL() {
        URL tempURL = null;
        try {
            tempURL = new URL(API_URL);
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

    public static ArrayList<News> extractBooks(String rawJSON) {
        ArrayList<News> currentNews = new ArrayList<News>();

        try {
            JSONObject jsonRoot = new JSONObject(rawJSON);
            JSONObject jsonResponse = jsonRoot.getJSONObject("response");
            JSONArray jsonResults = jsonResponse.getJSONArray("results");

            for (int i = 0; i < jsonResults.length(); i++) {

                JSONObject news = jsonResults.getJSONObject(i);
                jsonTitle = news.getString("webTitle");
                jsonLink = news.getString("webUrl");
                jsonDatePublication = news.getString("webPublicationDate").replaceAll("Z","");
                jsonCategory = news.getString("sectionName");

                JSONArray jsonTags = news.getJSONArray("tags");
                if (jsonTags.length() != 0) {
                    JSONObject jsonAuthorTag = jsonTags.getJSONObject(0);
                    jsonAuthor = jsonAuthorTag.getString("webTitle");
                } else {
                    jsonAuthor = "Unkown Author";
                }
                News currentNewsObject = new News(jsonTitle, jsonDatePublication, jsonAuthor, jsonCategory, jsonLink);
                currentNews.add(currentNewsObject);
            }

        } catch (JSONException e) {
            Log.e("NewsUtils", "Error Parsing the JSON");
        }
        return currentNews;
    }
}


