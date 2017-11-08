package com.alifyz.csbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.SearchView;

public class BookingActivity extends AppCompatActivity {

    private EditText searchBar;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_template);

        searchBar = (EditText) findViewById(R.id.keyword);
        searchBtn = (Button) findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultActivity = new Intent(BookingActivity.this, BookResult.class);
                resultActivity.putExtra("Keyword", searchBar.getText().toString());
                startActivity(resultActivity);
            }
        });

    }
}
