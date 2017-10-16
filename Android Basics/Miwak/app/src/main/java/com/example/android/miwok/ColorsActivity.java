package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("red","weṭeṭṭi", R.drawable.color_red));
        words.add(new Word("\t\n" + "green","chokokki", R.drawable.color_green));
        words.add(new Word("brown","\t\n" + "ṭakaakki" ,R.drawable.color_brown));
        words.add(new Word("gray","ṭopoppi", R.drawable.color_gray));
        words.add(new Word("black","\t\n" + "kululli", R.drawable.color_black));
        words.add(new Word("white","kelelli", R.drawable.color_white));


        WordAdapter wordAdapter = new WordAdapter(this, words);

        ListView listView = (ListView) findViewById(R.id.colors);
        listView.setAdapter(wordAdapter);
    }
}
