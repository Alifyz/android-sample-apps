package com.alifyz.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MoviesDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);

        TextView mMovieTitle = (TextView) findViewById(R.id.tv_movie_title);
        TextView mMovieYear = (TextView) findViewById(R.id.tv_year);
        TextView mMovieRatings = (TextView) findViewById(R.id.tv_ratings);
        TextView mMovieDescription = (TextView) findViewById(R.id.tv_movie_description);
        ImageView mMoviePoster = (ImageView) findViewById(R.id.iv_poster_details);
        TextView mMovieDuration = (TextView) findViewById(R.id.tv_duration);


        Intent movieInfo = getIntent();
        String title = movieInfo.getStringExtra("Title");
        String description = movieInfo.getStringExtra("Description");
        String year = movieInfo.getStringExtra("Year");
        String posterUrl = movieInfo.getStringExtra("PosterImage");
        String ratings = movieInfo.getStringExtra("Ratings") + "/10";

        mMovieTitle.setText(title);
        mMovieYear.setText(year.substring(0, 4));
        mMovieRatings.setText(ratings);
        mMovieDescription.setText(description);
        mMovieDuration.setText(getString(R.string.show_duration));
        Picasso.with(getApplicationContext()).load(posterUrl).into(mMoviePoster);

    }
}
