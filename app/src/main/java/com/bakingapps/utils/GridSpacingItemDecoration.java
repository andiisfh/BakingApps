package com.bakingapps.utils;

/**
 * Created by Andi Insanudin on 16/08/2016.
 */

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerView item decoration - give equal margin around grid item
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;

    public GridSpacingItemDecoration(int spanCount, int spacing) {
        this.spanCount = spanCount;
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position

        if(position > 0 && position % 2 == 1){
            outRect.left = spacing;
            outRect.right = spacing / 2;
        }else {
            outRect.left = spacing / 2;
            outRect.right = spacing;
        }

        if (position > 0 && position <= spanCount) {
            outRect.top = spacing;
        }else {
            outRect.top = 0;
        }

        if(position == 0){
            outRect.bottom = 0;
        }else {
            outRect.bottom = spacing;
        }
    }
}
