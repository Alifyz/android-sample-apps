package com.example.android.miwok;

/**
 * Created by AlifyzFPires on 10/2/2017.
 */

public class Word {

    private String miwakTranslation;
    private String defautTranslation;


    public Word(String miwak, String translation)  {
        miwakTranslation = miwak;
        defautTranslation = translation;
    }

    public String getMiwakTranslation() {
        return miwakTranslation;
    }

    public String getDefautTranslation() {
        return defautTranslation;
    }
}
