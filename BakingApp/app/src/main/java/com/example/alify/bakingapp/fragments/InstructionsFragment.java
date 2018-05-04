package com.example.alify.bakingapp.fragments;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alify.bakingapp.R;
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
import com.squareup.picasso.Picasso;

/**
 * Created by alify on 2/26/2018.
 */

public class InstructionsFragment extends Fragment {

    private static final DefaultBandwidthMeter BANDWIDTH_METER =
            new DefaultBandwidthMeter();

    private Long mLastVideoPosition = 0L;
    private boolean mPlayerReadiness = true;
    private String mCurrentUrl;
    private String mThumbnailUrl;

    TextView mTextRecipeDescription;
    ImageView mStepThumbnail;

    SimpleExoPlayerView mPlayerView;
    SimpleExoPlayer mSimpleExoPlayer;


    public InstructionsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.instructions_layout, container, false);

        findViews(rootView);

        Bundle mDataSet = this.getArguments();

        if(savedInstanceState != null) {
            mCurrentUrl = savedInstanceState.getString("mCurrentUrl");
            mTextRecipeDescription.setText(savedInstanceState.getString("Description"));
            mLastVideoPosition = savedInstanceState.getLong("position");
            mPlayerReadiness = savedInstanceState.getBoolean("PlayerReadiness");
            mThumbnailUrl = savedInstanceState.getString("thumbnailURL");
        } else {
            mTextRecipeDescription.setText(mDataSet.getString("description"));
            mCurrentUrl = mDataSet.getString("videoUrl");
            mThumbnailUrl = mDataSet.getString("thumbnailURL");
        }

        if(mThumbnailUrl.equals("") || mThumbnailUrl.length() == 0){
            mStepThumbnail.setVisibility(View.GONE);
        } else {
            Picasso.with(getActivity()).load(mThumbnailUrl).into(mStepThumbnail);
        }


        return rootView;
    }

    private void initPlayer(String VideoUrl) {
        if (mSimpleExoPlayer == null) {
            TrackSelection.Factory mAdaptativeTrackSelector = new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
            TrackSelector mTrackSelector = new DefaultTrackSelector(mAdaptativeTrackSelector);

            LoadControl mLoadControl = new DefaultLoadControl();
            mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), mTrackSelector, mLoadControl);
            mPlayerView.setPlayer(mSimpleExoPlayer);

            MediaSource mMediaSource = buildMediaSource(Uri.parse(VideoUrl));
            mSimpleExoPlayer.prepare(mMediaSource);
            mSimpleExoPlayer.setPlayWhenReady(mPlayerReadiness);
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getActivity(), Util.getUserAgent(getActivity(), "BakingApp"));
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource videoSource = new ExtractorMediaSource(uri,
                dataSourceFactory, extractorsFactory, null, null);
        return videoSource;
    }

    private void releasePlayer() {
        if (mSimpleExoPlayer != null) {
            mSimpleExoPlayer.release();
            mSimpleExoPlayer = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("position", mLastVideoPosition);
        outState.putString("mCurrentUrl", mCurrentUrl);
        outState.putString("Description", mTextRecipeDescription.getText().toString());
        outState.putBoolean("PlayerReadiness", true);
        outState.putString("thumbnailURL", mThumbnailUrl);
    }

    public void findViews(View rootview) {
        mTextRecipeDescription = (TextView) rootview.findViewById(R.id.instructions_tv_step_description);
        mPlayerView = (SimpleExoPlayerView) rootview.findViewById(R.id.exoplayer_view);
        mPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.coffee));
        mStepThumbnail = (ImageView) rootview.findViewById(R.id.step_thumbnail);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(Util.SDK_INT <= 23) {
            if(mSimpleExoPlayer != null) {
                mPlayerReadiness = mSimpleExoPlayer.getPlayWhenReady();
                mLastVideoPosition = mSimpleExoPlayer.getCurrentPosition();
                releasePlayer();
            }
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            if(mSimpleExoPlayer != null) {
                mPlayerReadiness = mSimpleExoPlayer.getPlayWhenReady();
                mLastVideoPosition = mSimpleExoPlayer.getCurrentPosition();
                releasePlayer();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initPlayer(mCurrentUrl);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mSimpleExoPlayer != null || Util.SDK_INT <= 23) {
            releasePlayer();
            initPlayer(mCurrentUrl);
            mSimpleExoPlayer.seekTo(mLastVideoPosition);
            mSimpleExoPlayer.setPlayWhenReady(mPlayerReadiness);
        }
    }
}
