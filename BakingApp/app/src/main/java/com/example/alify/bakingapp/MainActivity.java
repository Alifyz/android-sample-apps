package com.example.alify.bakingapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.alify.bakingapp.network.NetworkUtils;
import com.example.alify.bakingapp.recipes.RecipeLoader;
import com.example.alify.bakingapp.recipes.RecipeObject;
import com.example.alify.bakingapp.cardview.CardViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<RecipeObject>>{

    private final int LOADER_ID = 0;
    private List<RecipeObject> mData;
    private String mLayoutTag;

    @BindView(R.id.rv_main)
    RecyclerView mRecyclerView;
    @BindView(R.id.loading_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.no_internet)
    LinearLayout mNoInternetLayout;
    @BindView(R.id.try_again)
    Button mTryAgainBtn;

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
        if(mLayoutTag == getString(R.string.layout_tablet)) {
            mRecyclerView.setLayoutManager(mGridLayoutManager);
        } else {
            mRecyclerView.setLayoutManager(mLinearLayout);
        }

        mRecyclerView.setHasFixedSize(true);


        if(NetworkUtils.isInternetOn(this)) {
            getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
        } else {
            mNoInternetLayout.setVisibility(View.VISIBLE);
            mTryAgainBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tryAgainBtn();
                }
            });
        }

    }

    @Override
    public Loader<List<RecipeObject>> onCreateLoader(int i, Bundle bundle) {

        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        mNoInternetLayout.setVisibility(View.GONE);

        return new RecipeLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<RecipeObject>> loader, List<RecipeObject> recipeObjects) {

        mData = recipeObjects;
        mAdapter = new CardViewAdapter(mData, this);
        mRecyclerView.setAdapter(mAdapter);

        mProgressBar.setVisibility(View.GONE);
        mNoInternetLayout.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<List<RecipeObject>> loader) {
        mData.clear();
    }

    private void tryAgainBtn() {
        if(NetworkUtils.isInternetOn(this)) {
            getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
        }
    }


}
