package com.alifyz.canadatour;

/**
 * Created by Alifyz Pires on 10/25/2017.
 * This Class Represent the each Place in the App
 */

public class Place {

    private String title;
    private String description;
    private int imageResourceId;

    public Place(String title, String description, int imageResourceId) {
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
