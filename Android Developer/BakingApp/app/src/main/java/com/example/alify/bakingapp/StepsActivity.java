package com.example.alify.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsActivity extends AppCompatActivity {

    private int mPosition = 0;
    private int mMaxItem;
    private HashMap<String, String> mInformation;


    @BindView(R.id.btn_previous) Button btnPrevious;
    @BindView(R.id.btn_next)     Button btnNext;
    @BindView(R.id.tv_step_description)
    TextView mTextRecipeDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.bind(this);


        Intent mData = getIntent();
        setTitle("Step - " + mPosition);

        mInformation = (HashMap<String, String>) mData.getSerializableExtra("stepsInfo");
        mMaxItem = getMaxSize(mInformation);

        mTextRecipeDescription.setText(mInformation.get("description_" + mPosition)); //Initial value


        //TODO - 1 Unpack the Information
        //TODO - 2 Assign the respective value into its views
        //TODO - 3 Create a Dynamic system to change the behavior of each one.

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stepNext();
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stepPrevious();
            }
        });

    }

    private int getMaxSize(HashMap<String, String> information) {
        return (information.size() / 5);
    }

    private void stepNext() {
        if(mPosition < mMaxItem) {
            mPosition++;
        }
        if(mPosition < mMaxItem) {
            setTitle("Step - " + mPosition);
            String recipeDescription = mInformation.get("description_" + mPosition);
            mTextRecipeDescription.setText(recipeDescription);
        }
    }

    private void stepPrevious() {
        if(mPosition >= 0) {
            mPosition--;
        }
        if(mPosition >= 0) {
            setTitle("Step - " + mPosition);
            String recipeDescription = mInformation.get("description_" + mPosition);
            mTextRecipeDescription.setText(recipeDescription);
        }
    }


}
