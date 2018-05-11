package com.alifyz.canadatour;

/**
 * Created by Alifyz Pires on 10/25/2017.
 * This Class Represent the each Place in the App
 */

public class Place {

    private int title;
    private int description;
    private int imageResourceId;

    public Place(int title, int description, int imageResourceId) {
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public int getTitle() {
        return title;
    }

    public int getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
