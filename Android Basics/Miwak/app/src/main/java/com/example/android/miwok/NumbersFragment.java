package com.example.android.miwok;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {


    private MediaPlayer audioPlayer;


    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_numbers, container, false);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one","lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two","otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three","toloosaku", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four","oyysiva", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five","filla",R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six","temmokka",R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven","kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight","kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine","wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten","\t\n" + "na’aacha", R.drawable.number_ten, R.raw.number_ten));



        WordAdapter wordAdapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
        

        ListView listView = (ListView) rootView.findViewById(R.id.rootView);
        listView.setAdapter(wordAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                audioPlayer = MediaPlayer.create(getActivity(), words.get(i).getResourceAudioId());
                audioPlayer.start();

                audioPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        releaseMediaPlayer();
                    }
                });
            }
        });

        return rootView;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (audioPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            audioPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            audioPlayer = null;
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

}
