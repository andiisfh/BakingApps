package com.bakingapps.ui.fragment;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bakingapps.R;
import com.bakingapps.ui.activity.StepActivity;
import com.bakingapps.utils.Converter;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;

import butterknife.BindView;

/**
 * Created by andiisfh on 19/09/17.
 */

public class StepFragment extends BaseFragment implements OnPreparedListener {

    @BindView(R.id.video_view)
    VideoView mVideoView;
    @BindView(R.id.tv_desc)
    TextView mTVDesc;

    public StepFragment() {
    }

    @Override
    protected int setLayoutResources() {
        return R.layout.fragment_step;
    }

    @Override
    protected void onViewReady(View view, Bundle bundle) {
        String title = getArguments().getString("title");
        String desc = getArguments().getString("desc");
        String url_video = getArguments().getString("url_video");

        ((StepActivity) getActivity()).getSupportActionBar().setTitle(title);

        if (!url_video.isEmpty()) {
            mVideoView.setOnPreparedListener(this);
            // For now we just picked an arbitrary item to play
            mVideoView.setVideoURI(Uri.parse(url_video));
        } else {
            mVideoView.setVisibility(View.GONE);
        }

        mTVDesc.setText(desc);
    }

    @Override
    public void onPrepared() {
        // Starts the video playback as soon as it is ready
        mVideoView.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mVideoView.release();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (!isTablet) {
            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                ((StepActivity) getActivity()).getSupportActionBar().show();
                mTVDesc.setVisibility(View.VISIBLE);
                mVideoView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Converter.dpToPx(getActivity(), 250)));
            } else {
                ((StepActivity) getActivity()).getSupportActionBar().hide();
                mTVDesc.setVisibility(View.GONE);
                mVideoView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        }
    }
}
