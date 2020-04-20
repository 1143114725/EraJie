package com.erajiezhang.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hacknife.carouselbanner.interfaces.CarouselImageFactory;

/**
 * CarouselBanner 自定义加载器
 * @author EraJieZhang
 * @data 2020/4/19
 */
public class ImageFactory implements CarouselImageFactory {
    @Override
    public void onLoadFactory(String url, ImageView view) {
        Glide.with(view).load(url).into(view);
    }
}
