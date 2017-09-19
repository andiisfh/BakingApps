package com.bakingapps.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakingapps.storage.RealmUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by Andi Insanudin on 14/12/2016.
 */

public abstract class XRecyclerFragment extends Fragment {

    private Context mContext;
    private Realm mRealm;

    private XRecyclerView mXRecyclerView;

    public boolean isLoading = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutResources(), container, false);
        ButterKnife.bind(this, view);

        mContext = getActivity();

        if(mRealm == null){
            mRealm = RealmUtils.getRealmInstance();
        }

        if(setRecyclerView() != null){
            mXRecyclerView = setRecyclerView();
        }

        if(setLayoutManager() != null){
            mXRecyclerView.setLayoutManager(setLayoutManager());
        }

        onViewReady(view);

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

        return view;
    }

    protected abstract int setLayoutResources();
    protected abstract XRecyclerView setRecyclerView();
    protected abstract XRecyclerView.LayoutManager setLayoutManager();
    protected abstract void onViewReady(View view);
    protected abstract void onPullRefresh();
    protected abstract void onScrollLoadMore();

    public Context getContext(){
        return mContext;
    }

    Realm getRealm(){
        return mRealm;
    }
}
