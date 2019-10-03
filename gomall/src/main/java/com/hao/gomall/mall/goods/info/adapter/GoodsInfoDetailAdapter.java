package com.hao.gomall.mall.goods.info.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.LinkedHashMap;

public class GoodsInfoDetailAdapter extends FragmentPagerAdapter {

    private final LinkedHashMap<String, Fragment> items;
    private final String[] tabTitles;

    public GoodsInfoDetailAdapter(FragmentManager fm, LinkedHashMap<String, Fragment> items, String[] tabTitle) {
        super(fm);
        this.items = items;
        this.tabTitles = tabTitle;
    }

    @Override
    public Fragment getItem(int i) {
        return items.get(tabTitles[i]);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
