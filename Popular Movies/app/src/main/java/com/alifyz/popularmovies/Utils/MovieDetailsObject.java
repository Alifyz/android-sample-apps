package com.alifyz.popularmovies.Utils;

/**
 * Created by alify on 1/20/2018.
 */

public class MovieDetailsObject {

    private String[] mTrailers;
    private String[] mComments;
    private String[] mAuthors;
    private String mDuration;


    public MovieDetailsObject(String[] mTrailers, String[] mComments, String[] mAuthors, String mDuration) {
        this.mTrailers = mTrailers;
        this.mComments = mComments;
        this.mAuthors = mAuthors;
        this.mDuration = mDuration;
    }

    public String[] getmAuthors() {
        return mAuthors;
    }

    public String[] getmTrailers() {
        return mTrailers;
    }

    public String[] getmComments() {
        return mComments;
    }

    public String getmDuration() {
        return mDuration;
    }
}
