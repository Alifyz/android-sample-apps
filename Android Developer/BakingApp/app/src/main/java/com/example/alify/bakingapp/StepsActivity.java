package com.example.alify.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;

public class StepsActivity extends AppCompatActivity {

    private int mPosition;
    private HashMap<String, String> mInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        Intent mData = getIntent();
        setTitle("Step - " + (mData.getIntExtra("id_position", 0) + 1));

        mInformation = (HashMap<String, String>) mData.getSerializableExtra("stepsInfo");
        mPosition = mData.getIntExtra("id_position", 0);

        //TODO - 1 Unpack the Information
        //TODO - 2 Assign the respective value into its views
        //TODO - 3 Create a Dynamic system to change the behavior of each one.




    }
}
