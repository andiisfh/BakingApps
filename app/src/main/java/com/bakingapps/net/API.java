package com.bakingapps.net;

import com.bakingappsdomain.model.RecipeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by andiisfh on 01/07/17.
 */

public interface API {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<RecipeModel>> getRecipe();
}
