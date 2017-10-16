package com.example.android.miwok;

/**
 * Created by AlifyzFPires on 10/2/2017.
 */

public class Word {

    private String miwakTranslation;
    private String defautTranslation;
    private int resourceImageId;



    public Word(String miwak, String translation)  {
        miwakTranslation = miwak;
        defautTranslation = translation;
    }

    public Word(String miwakTranslation, String defautTranslation, int resourceImageId) {
        this.miwakTranslation = miwakTranslation;
        this.defautTranslation = defautTranslation;
        this.resourceImageId = resourceImageId;
    }

    public String getMiwakTranslation() {
        return miwakTranslation;
    }

    public String getDefautTranslation() {
        return defautTranslation;
    }

    public int getResourceImageId() {
        return resourceImageId;
    }
}
