package com.alifyz.newsapp;

/**
 * Created by Alifyz on 11/10/2017.
 */

public class News {

    private String mTitle;
    private String mDescription;
    private String mDatePublication;
    private String mAuthor;
    private String mCategory;

    public News(String mTitle, String mDescription, String mDatePublication, String mAuthor, String mCategory) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mDatePublication = mDatePublication;
        this.mAuthor = mAuthor;
        this.mCategory = mCategory;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmDatePublication() {
        return mDatePublication;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmCategory() {
        return mCategory;
    }
}
