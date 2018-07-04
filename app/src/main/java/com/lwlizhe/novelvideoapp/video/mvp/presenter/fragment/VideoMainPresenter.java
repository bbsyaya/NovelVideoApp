package com.lwlizhe.novelvideoapp.video.mvp.presenter.fragment;

import android.app.Application;
import android.content.Intent;

import com.lwlizhe.basemodule.base.ActivityManager;
import com.lwlizhe.basemodule.mvp.BasePresenter;
import com.lwlizhe.novelvideoapp.common.CommonSubscriber;
import com.lwlizhe.novelvideoapp.video.api.entity.jsoup.DilidiliInfo;
import com.lwlizhe.novelvideoapp.video.mvp.contract.VideoMainContract;
import com.lwlizhe.novelvideoapp.video.mvp.ui.adapter.VideoMainAdapter;
import com.lwlizhe.novelvideoapp.video.mvp.ui.adapter.holder.VideoMainBannerHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/7/2 0002.
 */

public class VideoMainPresenter extends BasePresenter<VideoMainContract.Model, VideoMainContract.View> {

    private VideoMainAdapter mVideoMainAdapter;

    DilidiliInfo dilidiliInfo;


    @Inject
    public VideoMainPresenter(VideoMainContract.Model model, VideoMainContract.View rootView, ActivityManager mActivityManager, Application mApplication) {
        super(model, rootView);

        initAdapter();

    }

    private void initAdapter() {

        mVideoMainAdapter=new VideoMainAdapter();

        mVideoMainAdapter.setOnBannerItemClickListener(new VideoMainBannerHolder.OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position, DilidiliInfo.ScheudleBanner itemData) {

//                Intent videoIntent=new Intent();
//                mRootView.launchActivity();
            }
        });

        mRootView.setRecyclerAdapter(mVideoMainAdapter);

    }

    public void getData() {
        mModel.getDilidiliInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonSubscriber<DilidiliInfo>() {
                    @Override
                    public void onNext(DilidiliInfo dilidiliInfo) {
                        mVideoMainAdapter.setDilidiliInfo(dilidiliInfo);
                        mVideoMainAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onFailed(Throwable t) {

                    }
                });
    }
}