package com.alifyz.newsapp;

/**
 * Created by Alifyz on 11/10/2017.
 */

public class News {

    private String mTitle;
    private String mDatePublication;
    private String mAuthor;
    private String mCategory;
    private String mLink;

    public News(String mTitle, String mDatePublication, String mAuthor, String mCategory, String mLink) {
        this.mTitle = mTitle;

        this.mLink = mLink;
        this.mDatePublication = mDatePublication;
        this.mAuthor = mAuthor;
        this.mCategory = mCategory;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmLink() {
        return mLink;
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
