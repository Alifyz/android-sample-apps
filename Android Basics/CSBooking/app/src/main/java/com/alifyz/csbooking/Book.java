package com.alifyz.csbooking;

import android.graphics.Bitmap;

/**
 * Created by Alifyz on 11/7/2017.
 */

public class Book {

    private Bitmap mImageResource;
    private String mTitle;
    private String mAuthor;
    private String mDescripton;

    public Book(Bitmap mImageResource, String mTitle, String mAuthor, String mDescripton) {
        this.mImageResource = mImageResource;
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mDescripton = mDescripton;
    }

    public Book(String mTitle, String mAuthor, String mDescripton) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mDescripton = mDescripton;
    }

    public Bitmap getmImageResource() {
        return mImageResource;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmDescripton() {
        return mDescripton;
    }
}
