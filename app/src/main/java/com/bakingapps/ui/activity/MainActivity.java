package com.bakingapps.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bakingapps.R;
import com.bakingapps.ui.adapter.RecipeAdapter;
import com.bakingapps.utils.Converter;
import com.bakingapps.utils.GridSpacingItemDecoration;
import com.bakingappsdomain.model.RecipeModel;
import com.bakingappsdomain.presenter.RecipeImp;
import com.bakingappsdomain.view.RecipeView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends XRecyclerActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.xrv_lists)
    XRecyclerView mXRecyclerView;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected Toolbar setToolbar() {
        return mToolbar;
    }

    @Override
    protected String setToolbarTitle() {
        return null;
    }

    @Override
    protected boolean isToolbarHasBack() {
        return false;
    }

    @Override
    protected XRecyclerView setRecyclerView() {
        return mXRecyclerView;
    }

    @Override
    protected XRecyclerView.LayoutManager setLayoutManager() {
        int span;

        if (isTablet) {
            span = 3;
        } else {
            span = 1;
        }

        return new GridLayoutManager(this, span);
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        mXRecyclerView.setLoadingMoreEnabled(false); // Disable the loadmore function

        int span;
        if (isTablet) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            span = 3;
        } else {
            span = 1;
        }
        mXRecyclerView.addItemDecoration(new GridSpacingItemDecoration(span, Converter.dpToPx(this, 5)));
        mXRecyclerView.setHasFixedSize(true);

        getRecipe(this);
    }

    @Override
    protected void onPullRefresh() {
        getRecipe(this);
    }

    @Override
    protected void onScrollLoadMore() {
        /* Do Nothing */
    }

    private void getRecipe(final Context context) {
        (new RecipeImp(this, new RecipeView() {
            @Override
            public void onSuccess(List<RecipeModel> recipeModel) {
                mXRecyclerView.refreshComplete();
                RecipeAdapter recipeAdapter = new RecipeAdapter(context, recipeModel);
                itemClickHandler(recipeAdapter);
                mXRecyclerView.setAdapter(recipeAdapter);
            }

            @Override
            public void onError(String message) {
                mXRecyclerView.refreshComplete();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        })).getRecipe();
    }

    private void itemClickHandler(RecipeAdapter recipeAdapter) {
        recipeAdapter.OnItemClick(new RecipeAdapter.Callback() {
            @Override
            public void onItemClick(RecipeModel recipeModel) {
                String recipeDatas = (new Gson()).toJson(recipeModel);
                startActivity(new Intent(getApplicationContext(), RecipeDetailActivity.class).putExtra("title", recipeModel.getName()).putExtra("recipeDatas", recipeDatas));
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (!isTablet) {
            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

            } else {

            }
        }
    }
}
