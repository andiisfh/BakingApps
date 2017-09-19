package com.bakingapps.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.bakingapps.R;
import com.bakingapps.ui.fragment.StepFragment;
import com.bakingapps.utils.NonSwipeableViewPager;
import com.bakingapps.utils.ViewPagerAdapter;
import com.bakingappsdomain.model.StepModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;

import static com.bakingapps.R.string.step;

/**
 * Created by andiisfh on 19/09/17.
 */

public class StepActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewPager)
    NonSwipeableViewPager mNonSwipeableViewPager;
    @BindView(R.id.btn_prev)
    Button mButtonPrev;
    @BindView(R.id.btn_next)
    Button mButtonNext;

    private List<StepModel> stepModel;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_step;
    }

    @Override
    protected Toolbar setToolbar() {
        return mToolbar;
    }

    @Override
    protected int setIconColor() {
        return android.R.color.white;
    }

    @Override
    protected String setToolbarTitle() {
        return getResources().getString(step);
    }

    @Override
    protected int setTitleColor() {
        return android.R.color.white;
    }

    @Override
    protected boolean isToolbarHasBack() {
        return true;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        String stepDatas = getIntent().getExtras().getString("stepDatas");
        int position = getIntent().getExtras().getInt("position");
        String title = getIntent().getExtras().getString("title");

        Type type = new TypeToken<List<StepModel>>() {
        }.getType();
        stepModel = (new Gson()).fromJson(stepDatas, type);

        setData(stepModel, position, title);

        if (position == 0) {
            mButtonPrev.setVisibility(View.GONE);
            mButtonNext.setVisibility(View.VISIBLE);
        } else if (position == stepModel.size() - 1) {
            mButtonPrev.setVisibility(View.VISIBLE);
            mButtonNext.setVisibility(View.GONE);
        } else {
            mButtonPrev.setVisibility(View.VISIBLE);
            mButtonNext.setVisibility(View.VISIBLE);
        }

        mButtonPrev.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
    }

    private void setData(List<StepModel> stepModel, int position, String title) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        for (int i = 0; i < stepModel.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            bundle.putString("desc", stepModel.get(i).getDescription());
            bundle.putString("url_video", stepModel.get(i).getVideoURL());
            StepFragment stepFragment = new StepFragment();
            stepFragment.setArguments(bundle);
            viewPagerAdapter.addFragment(stepFragment);
        }

        mNonSwipeableViewPager.setAdapter(viewPagerAdapter);
        int limit = (viewPagerAdapter.getCount() > 1 ? viewPagerAdapter.getCount() - 1 : 1);
        mNonSwipeableViewPager.setOffscreenPageLimit(limit);

        mNonSwipeableViewPager.setCurrentItem(position);
    }

    @Override
    public void onClick(View v) {
        int current = mNonSwipeableViewPager.getCurrentItem();

        if (current == 0) {
            mButtonPrev.setVisibility(View.GONE);
            mButtonNext.setVisibility(View.VISIBLE);
        } else if (current > 0 ){
            mButtonPrev.setVisibility(View.VISIBLE);
            mButtonNext.setVisibility(View.VISIBLE);
        } else if (current == stepModel.size() - 1) {
            mButtonPrev.setVisibility(View.VISIBLE);
            mButtonNext.setVisibility(View.GONE);
        }

        if (v.getId() == R.id.btn_prev) {
            if (current == 0) {
                mNonSwipeableViewPager.setCurrentItem(current - 1);
            }
        } else if (v.getId() == R.id.btn_next) {
            if (current < stepModel.size()) {
                mNonSwipeableViewPager.setCurrentItem(current + 1);
            }
        }
    }
}
