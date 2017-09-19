package com.bakingapps.utils;

/**
 * Created by Andi Insanudin on 16/08/2016.
 */

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * RecyclerView item decoration - give equal margin around grid item
 */
public class ListItemDecoration extends XRecyclerView.ItemDecoration{

    private int spacing;

    public ListItemDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        int position = parent.getChildAdapterPosition(view); // item position

        outRect.left = spacing * 2;
        outRect.right = spacing * 2;
        outRect.top = spacing;
        outRect.bottom = spacing;
    }
}
