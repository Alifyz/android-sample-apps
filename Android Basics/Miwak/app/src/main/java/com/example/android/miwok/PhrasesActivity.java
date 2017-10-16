package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("\t\n" + "Where are you going?","minto wuksus"));
        words.add(new Word("What is your name?","tinnә oyaase'nә"));
        words.add(new Word("My name is..." , "oyaaset..."));
        words.add(new Word("How are you feeling?","michәksәs?"));
        words.add(new Word("I’m feeling good." , "kuchi achit"));
        words.add(new Word("Are you coming?","әәnәs'aa?"));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm"));
        words.add(new Word("I’m coming.","yoowutis"));


        WordAdapter wordAdapter = new WordAdapter(this, words);

        ListView listView = (ListView) findViewById(R.id.phases);
        listView.setAdapter(wordAdapter);
    }
}
