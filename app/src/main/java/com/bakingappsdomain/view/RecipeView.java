package com.bakingappsdomain.view;

import com.bakingappsdomain.model.RecipeModel;

import java.util.List;

/**
 * Created by andiisfh on 18/09/17.
 */

public interface RecipeView {
    void onSuccess(List<RecipeModel> recipeModel);
    void onError(String message);
}
