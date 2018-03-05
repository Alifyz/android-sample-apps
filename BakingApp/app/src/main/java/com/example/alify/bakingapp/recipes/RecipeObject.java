package com.example.alify.bakingapp.recipes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;


/**
 * Created by alify on 2/9/2018.
 */

public class RecipeObject implements Parcelable {

    private String mRecipeName;
    private HashMap<String, String> mRecipes;
    private HashMap<String, String> mIngredients;
    private HashMap<String, String> mSteps;

    public RecipeObject(HashMap<String, String> mRecipes, HashMap<String, String> mIngredients,HashMap<String, String> mSteps, String recipeName) {
        this.mRecipes = mRecipes;
        this.mIngredients = mIngredients;
        this.mSteps = mSteps;
        this.mRecipeName = recipeName;
    }

    public HashMap<String, String> getmRecipes() {
        return mRecipes;
    }

    public HashMap<String, String> getmSteps() {
        return mSteps;
    }

    public HashMap<String, String> getmIngredients() {
        return mIngredients;
    }

    public String getmRecipeName() {
        return mRecipeName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.mRecipes);
        dest.writeSerializable(this.mIngredients);
        dest.writeSerializable(this.mSteps);
        dest.writeSerializable(this.mRecipeName);
    }

    protected RecipeObject(Parcel in) {
        this.mRecipes = (HashMap<String, String>) in.readSerializable();
        this.mIngredients = (HashMap<String, String>) in.readSerializable();
        this.mSteps = (HashMap<String, String>) in.readSerializable();
        this.mRecipeName = (String) in.readSerializable();
    }

    public static final Parcelable.Creator<RecipeObject> CREATOR = new Parcelable.Creator<RecipeObject>() {
        @Override
        public RecipeObject createFromParcel(Parcel source) {
            return new RecipeObject(source);
        }

        @Override
        public RecipeObject[] newArray(int size) {
            return new RecipeObject[size];
        }
    };
}
