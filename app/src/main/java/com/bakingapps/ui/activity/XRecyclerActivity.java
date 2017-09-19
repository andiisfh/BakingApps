package com.bakingapps.ui.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bakingapps.R;
import com.bakingapps.storage.RealmUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by Andi Insanudin on 16/02/2017.
 */

public abstract class XRecyclerActivity extends AppCompatActivity {

    private Realm mRealm;
    private XRecyclerView mXRecyclerView;
    private boolean mIsTablet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResource());
        ButterKnife.bind(this);

        setttingToolbar(setToolbar(), setToolbarTitle(), isToolbarHasBack());

        if(mRealm == null){
            mRealm = RealmUtils.getRealmInstance();
        }

        if(setRecyclerView() != null){
            mXRecyclerView = setRecyclerView();
        }

        if(setLayoutManager() != null){
            mXRecyclerView.setLayoutManager(setLayoutManager());
        }

        mIsTablet = getResources().getBoolean(R.bool.isTablet);

        onViewReady(savedInstanceState);

        if(mXRecyclerView != null){
            mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {
                    onPullRefresh();
                }

                @Override
                public void onLoadMore() {
                    onScrollLoadMore();
                }
            });
        }
    }

    protected abstract int setLayoutResource();
    protected abstract Toolbar setToolbar();
    protected abstract String setToolbarTitle();
    protected abstract boolean isToolbarHasBack();

    protected void setttingToolbar(Toolbar toolbar, String title, boolean hasBack) {
        if (toolbar != null) {
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setTitle(title);
            setSupportActionBar(toolbar);

            if(hasBack){
                toolbarBackDrawable();
                toolbarBackPressed(toolbar);
            }
        }
    }

    public void toolbarBackDrawable() {
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.ic_back);
        upArrow.setColorFilter(ContextCompat.getColor(this, android.R.color.white), PorterDuff.Mode.SRC_ATOP);
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

    protected abstract XRecyclerView setRecyclerView();
    protected abstract XRecyclerView.LayoutManager setLayoutManager();
    protected abstract void onViewReady(Bundle savedInstanceState);
    protected abstract void onPullRefresh();
    protected abstract void onScrollLoadMore();

    public boolean isTablet = mIsTablet;
    public Realm getRealm(){
        return mRealm;
    }

}
