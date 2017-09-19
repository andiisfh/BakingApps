package com.bakingapps.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bakingapps.R;
import com.bakingapps.ui.adapter.RecipeDetailAdapter;
import com.bakingapps.utils.Converter;
import com.bakingapps.utils.ListItemDecoration;
import com.bakingappsdomain.model.RecipeModel;
import com.bakingappsdomain.model.StepModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;

/**
 * Created by andiisfh on 19/09/17.
 */

public class RecipeDetailActivity extends XRecyclerActivity {

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
        return getIntent().getExtras().getString("title");
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

        String recipeDatas = getIntent().getExtras().getString("recipeDatas");
        Type type = new TypeToken<RecipeModel>(){}.getType();
        RecipeModel recipeModel = (new Gson()).fromJson(recipeDatas, type);

        setData(recipeModel);
    }

    @Override
    protected void onPullRefresh() {

    }

    @Override
    protected void onScrollLoadMore() {

    }

    private void setData(final RecipeModel recipeModel) {
        mXRecyclerView.setLoadingMoreEnabled(false); // Disable the loadmore function
        mXRecyclerView.setPullRefreshEnabled(false); // Disable the loadmore function

        mXRecyclerView.addItemDecoration(new ListItemDecoration(Converter.dpToPx(this, 5)));
        mXRecyclerView.setHasFixedSize(true);

        RecipeDetailAdapter recipeDetailAdapter = new RecipeDetailAdapter(this, recipeModel.getSteps());
        itemClickHandler(recipeDetailAdapter);
        mXRecyclerView.setAdapter(recipeDetailAdapter);

        View header = LayoutInflater.from(this).inflate(R.layout.header_detail_recipe, (ViewGroup) findViewById(android.R.id.content), false);
        TextView name = (TextView) header.findViewById(R.id.tv_name);
        name.setText(getResources().getString(R.string.ingredients));

        CardView container = (CardView) header.findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ingredientDatas = (new Gson()).toJson(recipeModel.getIngredients());
                startActivity(new Intent(getApplicationContext(), IngredientActivity.class).putExtra("ingredientDatas", ingredientDatas));
            }
        });

        mXRecyclerView.addHeaderView(header);
    }

    private void itemClickHandler(RecipeDetailAdapter recipeDetailAdapter) {
        recipeDetailAdapter.OnItemClick(new RecipeDetailAdapter.Callback() {
            @Override
            public void onItemClick(List<StepModel> stepModel, int position, String title) {
                String stepDatas = (new Gson()).toJson(stepModel);
                Intent intent = new Intent(getApplicationContext(), StepActivity.class);
                intent.putExtra("stepDatas", stepDatas);
                intent.putExtra("position", position);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
    }
}
