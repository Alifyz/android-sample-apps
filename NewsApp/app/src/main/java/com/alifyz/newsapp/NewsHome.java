package com.alifyz.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewsHome extends AppCompatActivity {

    private Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        btnLoad = (Button) findViewById(R.id.button);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loadNewsActivity = new Intent(NewsHome.this, NewsActivity.class);
                startActivity(loadNewsActivity);
            }
        });

    }
}
