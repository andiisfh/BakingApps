package com.bakingapps.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Andi Insanudin on 14/08/2016.
 */
public class Converter {

    /**
     * Converting dp to pixel
     */
    public static int dpToPx(Context context, int dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()));
    }

}
