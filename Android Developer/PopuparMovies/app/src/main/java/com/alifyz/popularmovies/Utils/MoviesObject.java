package com.alifyz.popularmovies.Utils;

/**
 * Created by Alifyz on 12/5/2017.
 */

public class MoviesObject {

    private String mMoviesTitle;
    private String mMoviesDescription;
    private String mReleaseDate;
    private String mMovieImage;
    private String mMovieRatings;



    public MoviesObject(String mMoviesTitle, String mMoviesDescription, String mReleaseDate, String mMovieImage,  String mMovieRatings) {
        this.mMoviesTitle = mMoviesTitle;
        this.mMoviesDescription = mMoviesDescription;
        this.mReleaseDate = mReleaseDate;
        this.mMovieImage = mMovieImage;
        this.mMovieRatings = mMovieRatings;

    }

    public String getmMoviesTitle() {
        return mMoviesTitle;
    }

    public String getmMoviesDescription() {
        return mMoviesDescription;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public String getmMovieImage() {
        return mMovieImage;
    }



    public String getmMovieRatings() {
        return mMovieRatings;
    }
}
