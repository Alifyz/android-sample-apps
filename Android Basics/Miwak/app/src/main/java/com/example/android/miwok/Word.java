package com.example.android.miwok;

/**
 * Created by Alifyz F Pires on 10/2/2017.
 * This Class represents the Building Blocks of Each Word in the App
 * Also, Store the basic characteristics and Information of each word.
 */

public class Word {

    private String miwakTranslation;
    private String defautTranslation;
    private int resourceImageId;
    private int resouceAudioId;


    // Three Different Constructors
    public Word(String miwak, String translation)  {
        miwakTranslation = miwak;
        defautTranslation = translation;
    }

    public Word(String miwakTranslation, String defautTranslation, int resourceImageId) {
        this.miwakTranslation = miwakTranslation;
        this.defautTranslation = defautTranslation;
        this.resourceImageId = resourceImageId;
    }

    public Word(String miwakTranslation, String defautTranslation, int resourceImageId, int resouceAudioId) {
        this.miwakTranslation = miwakTranslation;
        this.defautTranslation = defautTranslation;
        this.resourceImageId = resourceImageId;
        this.resouceAudioId = resouceAudioId;
    }


    //Get Methods
    public String getMiwakTranslation() {
        return miwakTranslation;
    }

    public String getDefautTranslation() {
        return defautTranslation;
    }

    public int getResourceImageId() {
        return resourceImageId;
    }

    public  int getResourceAudioId() {
        return resouceAudioId;
    }
}
