package com.hao.gomall.mall.ui;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

public class BannerHolderCreator implements CBViewHolderCreator<ImageHolder> {
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
