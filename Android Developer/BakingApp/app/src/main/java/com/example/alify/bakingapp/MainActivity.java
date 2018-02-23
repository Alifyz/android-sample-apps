package com.example.alify.bakingapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alify.bakingapp.Recipes.RecipeLoader;
import com.example.alify.bakingapp.Recipes.RecipeObject;
import com.example.alify.bakingapp.CardView.CardViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<RecipeObject>>{

    private final int LOADER_ID = 0;
    private static List<RecipeObject> mData;
    private String mLayoutTag;

    @BindView(R.id.rv_main) RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayout;
    GridLayoutManager mGridLayoutManager;
    CardViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);
        ButterKnife.bind(this);

        mLinearLayout = new LinearLayoutManager(this);
        mLinearLayout.setOrientation(LinearLayoutManager.VERTICAL);

        mGridLayoutManager = new GridLayoutManager(this,2);

        mLayoutTag = (String)mRecyclerView.getTag();
        if(mLayoutTag == getString(R.string.layout_tablet)
                || mLayoutTag == getString(R.string.layout_tablet_xlarge)) {
            mRecyclerView.setLayoutManager(mGridLayoutManager);
        } else {
            mRecyclerView.setLayoutManager(mLinearLayout);
        }

        mRecyclerView.setHasFixedSize(true);
        getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();

    }

    @Override
    public Loader<List<RecipeObject>> onCreateLoader(int i, Bundle bundle) {
        return new RecipeLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<RecipeObject>> loader, List<RecipeObject> recipeObjects) {
        mData = recipeObjects;
        mAdapter = new CardViewAdapter(mData, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<RecipeObject>> loader) {
        mData.clear();
    }

    public static List<RecipeObject> getmData() {
        return mData;
    }
}
