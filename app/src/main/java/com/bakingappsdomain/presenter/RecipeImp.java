package com.bakingappsdomain.presenter;

import android.content.Context;

import com.bakingapps.R;
import com.bakingapps.net.Config;
import com.bakingapps.net.ErrorExtractor;
import com.bakingappsdomain.model.RecipeModel;
import com.bakingappsdomain.view.RecipeView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andiisfh on 18/09/17.
 */

public class RecipeImp implements RecipePresenter {

    private Context mContext;
    private RecipeView mRecipeView;

    public RecipeImp(Context context, RecipeView recipeView){
        mContext = context;
        mRecipeView = recipeView;
    }

    @Override
    public void getRecipe() {
        Call<List<RecipeModel>> call = Config.getApi().getRecipe();
        call.enqueue(new Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                if(response.isSuccessful()){
                    mRecipeView.onSuccess(response.body());
                }else {
                    mRecipeView.onError(mContext.getResources().getString(R.string.request_failed));
                }
            }

            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                mRecipeView.onError(ErrorExtractor.getError(mContext, t));
            }
        });
    }
}
