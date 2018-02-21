package com.example.alify.bakingapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Debug;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alify.bakingapp.Recipes.RecipeObject;
import com.example.alify.bakingapp.RecipesFragment.IngredientsFragment;
import com.example.alify.bakingapp.RecipesFragment.MasterIngredientsFragment;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

public class StepsActivity extends AppCompatActivity {

    private int mPosition = 0;
    private int mMaxItem;
    private HashMap<String, String> mInformation;
    private String mVideoUrl;
    private int mRecipePosition;

    private static final DefaultBandwidthMeter BANDWIDTH_METER =
            new DefaultBandwidthMeter();

    @Nullable
    @BindView(R.id.btn_previous)
    Button btnPrevious;

    @Nullable
    @BindView(R.id.btn_next)
    Button btnNext;

    @Nullable
    @BindView(R.id.tv_step_description)
    TextView mTextRecipeDescription;

    @BindView(R.id.exoplayer_view)
    SimpleExoPlayerView mPlayerView;

    @Nullable
    SimpleExoPlayer mSimpleExoPlayer;

    @Nullable
    @BindView(R.id.master_fragment_container)
    FrameLayout mFrameContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.bind(this);

        Intent mData = getIntent();
        mRecipePosition = mData.getIntExtra("id", 0);

        setTitle("Step - " + mPosition);

        mInformation = (HashMap<String, String>) mData.getSerializableExtra("stepsInfo");
        mMaxItem = getMaxSize(mInformation);

        mVideoUrl = mInformation.get("videoURL_"+ mPosition);
        initPlayer(mVideoUrl);

        if(savedInstanceState == null) {
            mTextRecipeDescription.setText(mInformation.get("description_" + mPosition)); //Initial value
        } else {
            mTextRecipeDescription.setText(savedInstanceState.getString("description"));
            setTitle(savedInstanceState.getString("stepPosition"));
            mSimpleExoPlayer.seekTo(savedInstanceState.getLong("playerPosition"));
        }

        mPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.coffee));
        initializeButtons();

        if(mFrameContainer != null) {
            MasterIngredientsFragment masterIngredientsFragment = new MasterIngredientsFragment();

            List<RecipeObject> mGeneralInfo = MainActivity.getmData();
            HashMap<String, String> mInformation = mGeneralInfo.get(mRecipePosition).getmIngredients();

            Bundle metaData = new Bundle();
            metaData.putInt("id", mRecipePosition);
            metaData.putSerializable("ingredientsMetaData", mInformation);


            FragmentManager mFragmentManager = getFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            masterIngredientsFragment.setArguments(metaData);

            mFragmentTransaction.add(R.id.master_fragment_container, masterIngredientsFragment);
            mFragmentTransaction.commit();
        }

    }

    private int getMaxSize(HashMap<String, String> information) {
        return (information.size() / 5);
    }

    private void stepNext() {
        if(mPosition < mMaxItem) {
            mPosition++;
        }
        if(mPosition < mMaxItem) {
            releasePlayer();
            setTitle("Step - " + mPosition);
            String recipeDescription = mInformation.get("description_" + mPosition);
            mVideoUrl = mInformation.get("videoURL_"+ mPosition);
            initPlayer(mVideoUrl);
            mTextRecipeDescription.setText(recipeDescription);
        }
    }

    private void stepPrevious() {
        if(mPosition >= 0) {
            mPosition--;
        }
        if(mPosition >= 0) {
            releasePlayer();
            setTitle("Step - " + mPosition);
            String recipeDescription = mInformation.get("description_" + mPosition);
            mVideoUrl = mInformation.get("videoURL_"+ mPosition);
            initPlayer(mVideoUrl);
            mTextRecipeDescription.setText(recipeDescription);
        }
    }

    private void initPlayer(String VideoUrl) {
        if(mSimpleExoPlayer == null) {
            TrackSelection.Factory mAdaptativeTrackSelector = new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
            TrackSelector mTrackSelector = new DefaultTrackSelector(mAdaptativeTrackSelector);

            LoadControl mLoadControl = new DefaultLoadControl();
            mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, mTrackSelector, mLoadControl);
            mPlayerView.setPlayer(mSimpleExoPlayer);

            MediaSource mMediaSource = buildMediaSource(Uri.parse(VideoUrl));
            mSimpleExoPlayer.prepare(mMediaSource);
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
        if(mSimpleExoPlayer != null) {
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
        if(mTextRecipeDescription != null) {
            String recipeDescription = mTextRecipeDescription.getText().toString();
            outState.putString("description", recipeDescription);
        }
        String stepPosition = getTitle().toString();
        outState.putString("stepPosition", stepPosition);
        outState.putLong("playerPosition", mSimpleExoPlayer.getCurrentPosition());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }


}
