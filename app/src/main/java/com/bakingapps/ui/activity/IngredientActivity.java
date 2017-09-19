package com.bakingapps.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.bakingapps.R;
import com.bakingapps.ui.adapter.IngredientAdapter;
import com.bakingapps.utils.Converter;
import com.bakingapps.utils.ListItemDecoration;
import com.bakingappsdomain.model.IngredientModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;

/**
 * Created by andiisfh on 19/09/17.
 */

public class IngredientActivity extends XRecyclerActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.xrv_lists)
    XRecyclerView mXRecyclerView;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_recipe_detail;
    }

    @Override
    protected Toolbar setToolbar() {
        return mToolbar;
    }

    @Override
    protected String setToolbarTitle() {
        return getResources().getString(R.string.ingredients);
    }

    @Override
    protected boolean isToolbarHasBack() {
        return true;
    }

    @Override
    protected XRecyclerView setRecyclerView() {
        return mXRecyclerView;
    }

    @Override
    protected XRecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        if (isTablet) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        String ingredientDatas = getIntent().getExtras().getString("ingredientDatas");
        Type type = new TypeToken<List<IngredientModel>>(){}.getType();
        List<IngredientModel> ingredientModel = (new Gson()).fromJson(ingredientDatas, type);

        setData(ingredientModel);
    }

    @Override
    protected void onPullRefresh() {

    }

    @Override
    protected void onScrollLoadMore() {

    }

    private void setData(List<IngredientModel> ingredientModel) {
        mXRecyclerView.setLoadingMoreEnabled(false); // Disable the loadmore function
        mXRecyclerView.setPullRefreshEnabled(false); // Disable the loadmore function

        mXRecyclerView.addItemDecoration(new ListItemDecoration(Converter.dpToPx(this, 5)));
        mXRecyclerView.setHasFixedSize(true);

        IngredientAdapter ingredientAdapter = new IngredientAdapter(this, ingredientModel);
        itemClickHandler(ingredientAdapter);
        mXRecyclerView.setAdapter(ingredientAdapter);
    }

    private void itemClickHandler(IngredientAdapter ingredientAdapter) {
        ingredientAdapter.OnItemClick(new IngredientAdapter.Callback() {
            @Override
            public void onItemClick(IngredientModel ingredientModel) {

            }
        });
    }
}
