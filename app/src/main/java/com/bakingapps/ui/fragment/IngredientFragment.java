package com.bakingapps.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bakingapps.R;
import com.bakingappsdomain.model.IngredientModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.lang.reflect.Type;

import butterknife.BindView;

/**
 * Created by andiisfh on 19/09/17.
 */

public class IngredientFragment extends XRecyclerFragment {

    @BindView(R.id.xrv_lists)
    XRecyclerView mXRecyclerView;

    public IngredientFragment() {
    }

    @Override
    protected int setLayoutResources() {
        return R.layout.fragment_ingredient;
    }

    @Override
    protected XRecyclerView setRecyclerView() {
        return mXRecyclerView;
    }

    @Override
    protected XRecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void onViewReady(View view) {
        String ingredientdatas = getArguments().getString("ingredient_datas");

        Type type = new TypeToken<IngredientModel>(){}.getType();
        IngredientModel ingredientModel = (new Gson()).fromJson(ingredientdatas , type);


    }

    @Override
    protected void onPullRefresh() {

    }

    @Override
    protected void onScrollLoadMore() {

    }
}
