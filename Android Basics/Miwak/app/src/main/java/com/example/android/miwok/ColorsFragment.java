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
public class ColorsFragment extends Fragment {

    MediaPlayer audioPlayer;



    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_colors, container, false);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("red","weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("\t\n" + "green","chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown","\t\n" + "ṭakaakki" ,R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray","ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black","\t\n" + "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white","kelelli", R.drawable.color_white, R.raw.color_white));


        WordAdapter wordAdapter = new WordAdapter(getActivity(), words, R.color.category_colors);

        ListView listView = (ListView) rootView.findViewById(R.id.colors);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releaseMediaPlayer();
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


    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
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

}
