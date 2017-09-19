package com.bakingapps.ui.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bakingapps.R;

import butterknife.ButterKnife;

/**
 * Created by andiisfh on 04/08/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private boolean mIsTablet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResource());
        ButterKnife.bind(this);

        settingToolbar(setToolbar(), setIconColor(), setToolbarTitle(), setTitleColor(), isToolbarHasBack());

        mIsTablet = getResources().getBoolean(R.bool.isTablet);

        onViewReady(savedInstanceState);
    }

    protected abstract int setLayoutResource();
    protected abstract Toolbar setToolbar();
    protected abstract int setIconColor();
    protected abstract String setToolbarTitle();
    protected abstract int setTitleColor();
    protected abstract boolean isToolbarHasBack();

    protected void settingToolbar(Toolbar toolbar, int iconColor, String title, int titleColor, boolean hasBack) {
        if (toolbar != null) {
            toolbar.setTitle(title);
            toolbar.setTitleTextColor(ContextCompat.getColor(this, titleColor));
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);

            if(hasBack){
                toolbarBackDrawable(iconColor);
                toolbarBackPressed(toolbar);
            }
        }
    }

    public void toolbarBackDrawable(int color) {
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.ic_back);
        upArrow.setColorFilter(ContextCompat.getColor(this, color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void toolbarBackPressed(Toolbar toolbar) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public boolean isTablet = mIsTablet;
    protected abstract void onViewReady(Bundle savedInstanceState);
}