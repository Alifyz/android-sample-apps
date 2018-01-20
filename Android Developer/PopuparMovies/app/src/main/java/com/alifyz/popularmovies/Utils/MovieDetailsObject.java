package com.alifyz.popularmovies.Utils;

/**
 * Created by alify on 1/20/2018.
 */

public class MovieDetailsObject {

    private String[] mTrailers;
    private String[] mComments;
    private String[] mAuthors;



    private String mDuration;

    public MovieDetailsObject(String[] mTrailers, String[] mComments, String mDuration) {
        this.mTrailers = mTrailers;
        this.mComments = mComments;
        this.mDuration = mDuration;
    }

    public MovieDetailsObject(String[] mTrailers, String[] mComments) {
        this.mTrailers = mTrailers;
        this.mComments = mComments;
    }

    public MovieDetailsObject(String[] mTrailers, String[] mComments, String[] mAuthors) {
        this.mTrailers = mTrailers;
        this.mComments = mComments;
        this.mAuthors = mAuthors;
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
