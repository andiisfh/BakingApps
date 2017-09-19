package com.bakingapps.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakingapps.R;

import butterknife.ButterKnife;

/**
 * Created by Andi Insanudin on 22/02/2017.
 */

public abstract class BaseFragment extends Fragment {

    private Context mContext;
    private boolean mIsTablet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutResources(), container, false);
        ButterKnife.bind(this, view);

        mContext = getActivity();
        mIsTablet = getResources().getBoolean(R.bool.isTablet);

        onViewReady(view, savedInstanceState);

        return view;
    }

    protected abstract int setLayoutResources();

    protected abstract void onViewReady(View view, Bundle bundle);

    public Context getContext() {
        return mContext;
    }

    public boolean isTablet = mIsTablet;

}
