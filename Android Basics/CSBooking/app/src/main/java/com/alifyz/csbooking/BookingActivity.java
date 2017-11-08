package com.alifyz.csbooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;

public class BookingActivity extends AppCompatActivity {

    private SearchView searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_template);

        searchBar = (SearchView) findViewById(R.id.search_view);
    }
}
