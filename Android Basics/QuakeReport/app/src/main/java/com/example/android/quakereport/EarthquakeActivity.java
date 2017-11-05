/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    public static final String RAW_JSON = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ArrayList<Earthquake> earthquakes = EarthQuakeUtils.extractEarthquakes();

        // Find a reference to the {@link ListView} in the layout
        final ListView earthquakeListView = (ListView) findViewById(R.id.list);

        //Open an Intent Service to Browse for certain EarthQuake
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView offsetText = (TextView) view.findViewById(R.id.location_offset);
                TextView location = (TextView) view.findViewById(R.id.primary_location);

                StringBuilder keyword = new StringBuilder();
                keyword.append(offsetText.getText());
                keyword.append(location.getText());

                Intent goBroswer = new Intent(Intent.ACTION_WEB_SEARCH);
                goBroswer.putExtra(SearchManager.QUERY, keyword.toString());
                startActivity(goBroswer);

            }
        });

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);


    }

    private class EarthquakeAsync extends AsyncTask<String, Void, List<Earthquake>> {

        @Override
        protected List<Earthquake> doInBackground(String... strings) {
            URL SERVER_URL = parseURL(strings[0]);
            String jsonResponse = null;

            try {
                jsonResponse = makeHTTPRequest(SERVER_URL);

            } catch (IOException e) {
                //TODO Handle Exception
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            super.onPostExecute(earthquakes);
        }

        protected URL parseURL(String url) {
            URL SERVER_URL = null;
            try {
                SERVER_URL = new URL(url);
            } catch (MalformedURLException e) {
                return null;
            }
            return SERVER_URL;
        }

        protected String makeHTTPRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;

            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readInputStream(inputStream);
                }
            } catch (IOException e) {
                //TODO Handle the Execption
            }

            //After the Handling the Exception we Close the Connection and the Input Reader
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        //Read and Translate the InputStream Received by the GET Request
        protected String readInputStream(InputStream inputStream) throws IOException {
            StringBuilder rawJSON = new StringBuilder();
            if(inputStream != null) {
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
    }
}
