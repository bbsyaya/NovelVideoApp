package com.lwlizhe.novelvideoapp.video.mvp.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.lwlizhe.basemodule.base.adapter.BaseHolder;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageConfig;
import com.lwlizhe.basemodule.imageloader.glide.GlideImageLoaderStrategy;
import com.lwlizhe.novelvideoapp.AppApplication;
import com.lwlizhe.novelvideoapp.GlobeConstance;
import com.lwlizhe.novelvideoapp.R;
import com.lwlizhe.novelvideoapp.video.api.entity.jsoup.DilidiliInfo;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public class VideoMainBannerHolder extends BaseHolder<List<DilidiliInfo.ScheudleBanner>> {

    private Banner mBanner;
    private Context mContext;

    private List<DilidiliInfo.ScheudleBanner> mBannerData;

    private GlideImageLoaderStrategy mImageLoader;

    public VideoMainBannerHolder(View itemView) {
        super(itemView);

        mContext = itemView.getContext();
        mBannerData = new ArrayList<>();

        mImageLoader = ((AppApplication) mContext.getApplicationContext()).getAppComponent().mGlideImageLoader();

        mBanner = itemView.findViewById(R.id.banner);

    }

    @Override
    public void setData(List<DilidiliInfo.ScheudleBanner> data, int position) {

        mBannerData.clear();
        mBannerData.addAll(data);

        setBannerData(mBannerData);
    }

    public void setBannerData(List<DilidiliInfo.ScheudleBanner> mBannerData) {

        ArrayList<String> bannerImages = new ArrayList<>();
        ArrayList<String> bannerTitles = new ArrayList<>();


        for (DilidiliInfo.ScheudleBanner data : mBannerData) {
            bannerImages.add(data.getImgUrl());
            bannerTitles.add(data.getName());
        }

        setBanner(bannerImages, bannerTitles);

    }

    public void setBanner(ArrayList<String> images, ArrayList<String> titles) {
        mBanner.setImageLoader(new com.youth.banner.loader.ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                mImageLoader.loadImage(mContext, GlideImageConfig
                        .builder()
                        .url((String) path)
                        .imageView(imageView)
                        .refererUrl(GlobeConstance.DMZJ_IMG_REFERER_URL)
                        .build());
            }
        });

        mBanner.setImages(images);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setBannerAnimation(Transformer.Default);
        mBanner.setBannerTitles(titles);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(3000);
        mBanner.start();
    }

    public void setBannerClickListener(final OnBannerClickListener listener) {
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                listener.OnBannerClick(position, mBannerData.get(position));
            }
        });
    }

    public interface OnBannerClickListener {
        void OnBannerClick(int position, DilidiliInfo.ScheudleBanner itemData);
    }


}
