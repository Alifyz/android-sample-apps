package com.example.alify.bakingapp.RecipesFragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by alify on 2/22/2018.
 */

public class MasterStepsFragment extends Fragment {

    private static final DefaultBandwidthMeter BANDWIDTH_METER =
            new DefaultBandwidthMeter();

    SimpleExoPlayer mSimpleExoPlayer;
    SimpleExoPlayerView mPlayerView;
    TextView mRecipeDescription;

    public MasterStepsFragment() {}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.master_steps_fragment, container, false);

        //Binding Views
        mPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.exoplayer_view);
        mRecipeDescription = (TextView) rootView.findViewById(R.id.tv_step_description);

        updateInfo(mRecipeDescription);


        return rootView;
    }

    private void initPlayer(String VideoUrl) {
        if(mSimpleExoPlayer == null) {
            TrackSelection.Factory mAdaptativeTrackSelector = new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
            TrackSelector mTrackSelector = new DefaultTrackSelector(mAdaptativeTrackSelector);

            LoadControl mLoadControl = new DefaultLoadControl();
            mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), mTrackSelector, mLoadControl);
            mPlayerView.setPlayer(mSimpleExoPlayer);

            MediaSource mMediaSource = buildMediaSource(Uri.parse(VideoUrl));
            mSimpleExoPlayer.prepare(mMediaSource);
            mSimpleExoPlayer.setPlayWhenReady(true);
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


    public void updateInfo(TextView recipeDetails) {
        recipeDetails.setText("Alify here");
    }
}
