package com.alifyz.csbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class BookResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_template);

        //Extract the Keyword from the HomeActivity
        Intent intentReceiver = getIntent();
        String keywords = intentReceiver.getStringExtra("Keyword");

    }
}
