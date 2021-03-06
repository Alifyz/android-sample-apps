package com.alifyz.popularmovies;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alifyz.popularmovies.Database.MoviesContract.MoviesEntry;
import com.alifyz.popularmovies.Utils.MovieDetailsLoader;
import com.alifyz.popularmovies.Utils.MovieDetailsObject;
import com.alifyz.popularmovies.Utils.NetworkUtils;
import com.squareup.picasso.Picasso;


public class MoviesDetailsActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<MovieDetailsObject> {

    private final String TAG = "MoviesDetailsActivity";

    private String[] mCurrentTrailer;
    private String[] mCurrentComment;
    private String[] mCurrentAuthors;

    private final int mFirstItem = 0;
    private final int mSecondItem = 1;
    private final int mThirdItem = 2;

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

        Intent movieInfo = getIntent();
        mTitle = movieInfo.getStringExtra("Title");
        mDescription = movieInfo.getStringExtra("Description");
        mYear = movieInfo.getStringExtra("Year");
        mPosterUrl = movieInfo.getStringExtra("PosterImage");
        mRatings = movieInfo.getStringExtra("Ratings") + "/10";
        mId = movieInfo.getStringExtra("Id");

        getLoaderManager().initLoader(Integer.parseInt(mId), null, this).forceLoad();

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
                if (isDownloadFinished) {
                    if (!isMovieAlreadyAdded(mMovieTitle.getText().toString())) {
                        ContentValues values = new ContentValues();
                        values.put(MoviesEntry.COLUMN_TITLE, mMovieTitle.getText().toString());
                        values.put(MoviesEntry.COLUMN_RELEASE_DATE, mMovieYear.getText().toString());
                        values.put(MoviesEntry.COLUMN_RATING, mMovieRatings.getText().toString());
                        values.put(MoviesEntry.COLUMN_DESCRIPTION, mMovieDescription.getText().toString());
                        values.put(MoviesEntry.COLUMN_IMAGE, mPosterUrl);
                        values.put(MoviesEntry.COLUMN_DURATION, mDuration.getText().toString());
                        values.put(MoviesEntry.COLUMN_TRAILER, mCurrentTrailer[mFirstItem]);
                        getContentResolver().insert(MoviesEntry.CONTENT_MOVIES, values);
                    } else {
                        Toast.makeText(MoviesDetailsActivity.this, "Movie already added", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MoviesDetailsActivity.this, "Wait for the download to end..", Toast.LENGTH_SHORT).show();
                }
            }
        });


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
                    trailerIntent.setData(Uri.parse(mCurrentTrailer[mFirstItem]));
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
                    trailerIntent.setData(Uri.parse(mCurrentTrailer[mSecondItem]));
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
                    trailerIntent.setData(Uri.parse(mCurrentTrailer[mThirdItem]));
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
            for(int i = 0; i < mCurrentAuthors.length; i++) {
                mComment1.setText(mCurrentComment[i]);
                mAuthor1.setText(mCurrentAuthors[i]);

                mComment2.setText(mCurrentComment[i]);
                mAuthor2.setText(mCurrentAuthors[i]);

                mComment3.setText(mCurrentComment[i]);
                mAuthor3.setText(mCurrentAuthors[i]);
            }

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

    private boolean isMovieAlreadyAdded(String title) {

        Cursor movie = getContentResolver().query(MoviesEntry.CONTENT_MOVIES, new String[]{MoviesEntry.COLUMN_TITLE},
                MoviesEntry.COLUMN_TITLE + " = ?", new String[]{title}, null, null);
        try {
            movie.moveToFirst();
            int titleColumnIndex = movie.getColumnIndex(MoviesEntry.COLUMN_TITLE);
            String titleData = movie.getString(titleColumnIndex);
            if (title.equals(titleData)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error trying to verify if the movie is already in the favorites database");
            return false;
        }

    }
}
