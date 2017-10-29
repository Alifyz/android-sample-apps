package com.alifyz.canadatour;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Nature extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        //Connecting the Activity to the Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NatureFragment())
                .commit();
    }
}
