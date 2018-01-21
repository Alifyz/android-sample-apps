package com.alifyz.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MoviesFavoritesDetailsActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mYear;
    private TextView mDuration;
    private TextView mRatings;
    private TextView mDescription;

    private ImageView mPosterImage;
    private ImageView mTrailerIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_favorites);
        setTitle(R.string.favorites_title);

        mTitle = (TextView) findViewById(R.id.tv_movie_title_fav);
        mYear = (TextView) findViewById(R.id.tv_year_fav);
        mDuration = (TextView) findViewById(R.id.tv_duration_fav);
        mRatings = (TextView) findViewById(R.id.tv_ratings_fav);
        mDescription = (TextView) findViewById(R.id.tv_movie_description_fav);

        mPosterImage = (ImageView) findViewById(R.id.iv_poster_details_fav);
        mTrailerIcon = (ImageView) findViewById(R.id.movie_trailer_icon_fav);

        Intent favorites = getIntent();

        String poster = favorites.getStringExtra("Poster");
        String title = favorites.getStringExtra("Title");
        String year = favorites.getStringExtra("Year");
        String duration = favorites.getStringExtra("Duration");
        String ratings = favorites.getStringExtra("Ratings");
        String description = favorites.getStringExtra("Description");
        final String trailer = favorites.getStringExtra("Trailer");


        mTitle.setText(title);
        mYear.setText(year);
        mDuration.setText(duration);
        mRatings.setText(ratings);
        mDescription.setText(description);

        Picasso.with(getApplicationContext()).load(poster).into(mPosterImage);

        mTrailerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openTrailer = new Intent(Intent.ACTION_VIEW);
                openTrailer.setData(Uri.parse(trailer));
                if(openTrailer.resolveActivity(getPackageManager()) != null) {
                    startActivity(openTrailer);
                }
            }
        });

    }
}
