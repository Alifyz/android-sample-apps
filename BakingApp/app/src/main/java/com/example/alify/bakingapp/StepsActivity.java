package com.example.alify.bakingapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsActivity extends AppCompatActivity {

    private int mPosition;
    private HashMap<String, String> mInformation;

    private String mVideo;
    private String mDescription;
    private int mMaxItem;

    private Long mLastVideoPosition = 0l;
    private boolean mPlayerReadiness;
    private String mStepPosition;

    private static final DefaultBandwidthMeter BANDWIDTH_METER =
            new DefaultBandwidthMeter();


    @Nullable
    @BindView(R.id.btn_previous)
    Button btnPrevious;

    @Nullable
    @BindView(R.id.btn_next)
    Button btnNext;

    @BindView(R.id.instructions_tv_step_description)
    TextView mTextRecipeDescription;

    @BindView(R.id.exoplayer_view)
    SimpleExoPlayerView mPlayerView;

    SimpleExoPlayer mSimpleExoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        setTitle("Instructions");
        ButterKnife.bind(this);

        mPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.coffee));

        //Retrieving Data from the Intent
        Intent mData = getIntent();

        //Grab the Data
        mVideo = mData.getStringExtra("videoUrl");
        mDescription = mData.getStringExtra("description");
        mStepPosition = mData.getStringExtra("position");

        mPosition = Integer.parseInt(mStepPosition);

        //Data set
        mInformation = (HashMap<String, String>) mData.getSerializableExtra("stepsInfo");
        mMaxItem = getMaxSize(mInformation);


        //Linking Listeners to Each Buttons
        initializeButtons();

        //Restoring the Player Position
        if (savedInstanceState == null) {
            mVideo = mData.getStringExtra("videoUrl");
            mDescription = mData.getStringExtra("description");
            mPlayerReadiness = true;
        } else {
            mVideo = savedInstanceState.getString("videoUrl");
            mLastVideoPosition = savedInstanceState.getLong("playerPosition");
            mPlayerReadiness = savedInstanceState.getBoolean("player_status");
        }

        mTextRecipeDescription.setText(mDescription);
    }


    private int getMaxSize(HashMap<String, String> information) {
        return (information.size() / 5);
    }

    private void stepNext() {
        if (mPosition < mMaxItem) {
            mPosition++;
        }
        if (mPosition < mMaxItem) {
            releasePlayer();
            setTitle("Step - " + mPosition);
            mLastVideoPosition = 0L;
            mVideo = mInformation.get("videoURL_" + mPosition);
            initPlayer(mVideo);
            mTextRecipeDescription.setText(mInformation.get("description_" + mPosition));
        }
    }

    private void stepPrevious() {
        if (mPosition >= 0) {
            mPosition--;
        }
        if (mPosition >= 0) {
            releasePlayer();
            setTitle("Step - " + mPosition);
            mLastVideoPosition = 0L;
            mVideo = mInformation.get("videoURL_" + mPosition);
            initPlayer(mVideo);
            mTextRecipeDescription.setText(mInformation.get("description_" + mPosition));
        }
    }

    private void initPlayer(String VideoUrl) {
        if (mSimpleExoPlayer == null) {
            TrackSelection.Factory mAdaptativeTrackSelector = new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
            TrackSelector mTrackSelector = new DefaultTrackSelector(mAdaptativeTrackSelector);

            LoadControl mLoadControl = new DefaultLoadControl();
            mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, mTrackSelector, mLoadControl);
            mPlayerView.setPlayer(mSimpleExoPlayer);

            MediaSource mMediaSource = buildMediaSource(Uri.parse(VideoUrl));
            mSimpleExoPlayer.prepare(mMediaSource);
            mSimpleExoPlayer.seekTo(mLastVideoPosition);
            mSimpleExoPlayer.setPlayWhenReady(true);
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, Util.getUserAgent(this, "BakingApp"));
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource videoSource = new ExtractorMediaSource(uri,
                dataSourceFactory, extractorsFactory, null, null);
        return videoSource;
    }

    private void releasePlayer() {
        if (mSimpleExoPlayer != null) {
            mLastVideoPosition = mSimpleExoPlayer.getCurrentPosition();
            mPlayerReadiness = mSimpleExoPlayer.getPlayWhenReady();
            mSimpleExoPlayer.release();
            mSimpleExoPlayer = null;
        }
    }

    private void initializeButtons() {
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mTextRecipeDescription != null) {
            String recipeDescription = mTextRecipeDescription.getText().toString();
            outState.putString("description", recipeDescription);
        }
        String stepPosition = getTitle().toString();

        outState.putString("videoUrl", mVideo);
        outState.putString("stepPosition", stepPosition);
        outState.putLong("playerPosition", mSimpleExoPlayer.getCurrentPosition());
        outState.putBoolean("player_status", mSimpleExoPlayer.getPlayWhenReady());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTextRecipeDescription.setText(savedInstanceState.getString("description"));
        setTitle(savedInstanceState.getString("stepPosition"));
        mVideo = savedInstanceState.getString("videoUrl");
        mLastVideoPosition = savedInstanceState.getLong("playerPosition");
    }


    @Override
    public void onPause() {
        super.onPause();
        if(Util.SDK_INT <= 23) {
            if(mSimpleExoPlayer != null) {
                releasePlayer();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            if(mSimpleExoPlayer != null) {
                releasePlayer();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initPlayer(mVideo);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
          if(mSimpleExoPlayer != null || Util.SDK_INT <= 23) {
                initPlayer(mVideo);
                mSimpleExoPlayer.seekTo(mLastVideoPosition);
                mSimpleExoPlayer.setPlayWhenReady(mPlayerReadiness);
            }
    }
}
