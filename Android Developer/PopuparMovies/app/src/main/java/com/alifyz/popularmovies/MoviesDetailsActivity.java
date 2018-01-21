package com.alifyz.popularmovies;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.alifyz.popularmovies.Database.MoviesContract.MoviesEntry;
import com.alifyz.popularmovies.Utils.MovieDetailsLoader;
import com.alifyz.popularmovies.Utils.MovieDetailsObject;
import com.squareup.picasso.Picasso;


public class MoviesDetailsActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<MovieDetailsObject> {

    private String[] mCurrentTrailer;
    private String[] mCurrentComment;
    private String[] mCurrentAuthors;

    private TextView mReviewsTitle;

    private TextView mComment1;
    private TextView mAuthor1;

    private TextView mComment2;
    private TextView mAuthor2;

    private TextView mComment3;
    private TextView mAuthor3;

    private ProgressBar mDetailsProgressBar;
    private TextView mDuration;

    private boolean isDownloadFinished = false;

    private String mTitle;
    private String mDescription;
    private String mYear;
    private String mPosterUrl;
    private String mRatings;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movies_details);

        setTitle(getString(R.string.favorites_title));

        final TextView mMovieTitle = (TextView) findViewById(R.id.tv_movie_title);
        final TextView mMovieYear = (TextView) findViewById(R.id.tv_year);
        final TextView mMovieRatings = (TextView) findViewById(R.id.tv_ratings);
        final TextView mMovieDescription = (TextView) findViewById(R.id.tv_movie_description);
        final ImageView mMoviePoster = (ImageView) findViewById(R.id.iv_poster_details);


        final ImageView mTrailerIcon1 = (ImageView) findViewById(R.id.movie_trailer_icon);
        final ImageView mTrailerIcon2 = (ImageView) findViewById(R.id.movie_trailer_icon2);
        final ImageView mTrailerIcon3 = (ImageView) findViewById(R.id.movie_trailer_icon3);

        mDetailsProgressBar = (ProgressBar) findViewById(R.id.pbProcessing);

        mReviewsTitle = (TextView) findViewById(R.id.tv_author_1);

        mComment1 = (TextView) findViewById(R.id.comment1);
        mAuthor1 = (TextView) findViewById(R.id.author1);

        mComment2 = (TextView) findViewById(R.id.comment2);
        mAuthor2 = (TextView) findViewById(R.id.author2);

        mComment3 = (TextView) findViewById(R.id.comment3);
        mAuthor3 = (TextView) findViewById(R.id.author3);

        mDuration = (TextView) findViewById(R.id.tv_duration);

        ImageView mAddFavorite = (ImageView) findViewById(R.id.tv_add_favorite);

        mAddFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(MoviesEntry.COLUMN_TITLE, mMovieTitle.getText().toString());
                values.put(MoviesEntry.COLUMN_RELEASE_DATE, mMovieYear.getText().toString());
                values.put(MoviesEntry.COLUMN_RATING, mMovieRatings.getText().toString());
                values.put(MoviesEntry.COLUMN_DESCRIPTION, mMovieDescription.getText().toString());
                values.put(MoviesEntry.COLUMN_IMAGE, mPosterUrl);
                values.put(MoviesEntry.COLUMN_DURATION, mDuration.getText().toString());
                getContentResolver().insert(MoviesEntry.CONTENT_MOVIES, values);
            }
        });


        Intent movieInfo = getIntent();
        mTitle = movieInfo.getStringExtra("Title");
        mDescription = movieInfo.getStringExtra("Description");
        mYear = movieInfo.getStringExtra("Year");
        mPosterUrl = movieInfo.getStringExtra("PosterImage");
        mRatings = movieInfo.getStringExtra("Ratings") + "/10";
        mId = movieInfo.getStringExtra("Id");

        getLoaderManager().initLoader(Integer.parseInt(mId), null, this).forceLoad();


        mMovieTitle.setText(mTitle);
        mMovieYear.setText(mYear.substring(0, 4));
        mMovieRatings.setText(mRatings);
        mMovieDescription.setText(mDescription);

        Picasso.with(getApplicationContext()).load(mPosterUrl).into(mMoviePoster);


        mTrailerIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDownloadFinished) {
                    Intent trailerIntent = new Intent(Intent.ACTION_VIEW);
                    trailerIntent.setData(Uri.parse(mCurrentTrailer[0]));
                    if (trailerIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(trailerIntent);
                    }
                }
            }
        });

        mTrailerIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDownloadFinished) {
                    Intent trailerIntent = new Intent(Intent.ACTION_VIEW);
                    trailerIntent.setData(Uri.parse(mCurrentTrailer[1]));
                    if (trailerIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(trailerIntent);
                    }
                }
            }
        });

        mTrailerIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDownloadFinished) {
                    Intent trailerIntent = new Intent(Intent.ACTION_VIEW);
                    trailerIntent.setData(Uri.parse(mCurrentTrailer[2]));
                    if (trailerIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(trailerIntent);
                    }
                }
            }
        });


    }


    @Override
    public Loader<MovieDetailsObject> onCreateLoader(int id, Bundle bundle) {
        return new MovieDetailsLoader(this, id);
    }

    @Override
    public void onLoadFinished(Loader<MovieDetailsObject> loader, MovieDetailsObject movieDetailsObject) {

        String currentDuration = movieDetailsObject.getmDuration() + "m";
        mCurrentTrailer = movieDetailsObject.getmTrailers();
        mCurrentComment = movieDetailsObject.getmComments();
        mCurrentAuthors = movieDetailsObject.getmAuthors();

        isDownloadFinished = true;
        mDuration.setText(currentDuration);
        mDetailsProgressBar.setVisibility(View.GONE);

        if (mCurrentAuthors != null && mCurrentComment != null) {
            mReviewsTitle.setText(getString(R.string.found_Results));
            mComment1.setText(mCurrentComment[0]);
            mAuthor1.setText(mCurrentAuthors[0]);

            mComment2.setText(mCurrentComment[1]);
            mAuthor2.setText(mCurrentAuthors[1]);

            mComment3.setText(mCurrentComment[2]);
            mAuthor3.setText(mCurrentAuthors[2]);
        } else {
            mReviewsTitle.setText(getString(R.string.no_results));
            mComment1.setVisibility(View.GONE);
            mAuthor1.setVisibility(View.GONE);
            mComment2.setVisibility(View.GONE);
            mAuthor2.setVisibility(View.GONE);
            mComment3.setVisibility(View.GONE);
            mAuthor3.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(Loader<MovieDetailsObject> loader) {
        mCurrentTrailer = null;
        mCurrentComment = null;
    }
}
