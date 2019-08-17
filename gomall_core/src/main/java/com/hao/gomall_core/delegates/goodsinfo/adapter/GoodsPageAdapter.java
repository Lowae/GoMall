package com.hao.gomall_core.delegates.goodsinfo.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hao.gomall_core.delegates.MallDelegate;

import java.util.ArrayList;

public class GoodsPageAdapter extends FragmentPagerAdapter {

    private final ArrayList<MallDelegate> ITEM_DELEGATES;
    private final ArrayList<String> TAB_BEANS;


    public GoodsPageAdapter(FragmentManager fm, ArrayList<String> tabBeans, ArrayList<MallDelegate> item_delegates) {
        super(fm);
        this.TAB_BEANS = tabBeans;
        this.ITEM_DELEGATES = item_delegates;
    }

    @Override
    public Fragment getItem(int i) {
        return ITEM_DELEGATES.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_BEANS.get(position);
    }

    @Override
    public int getCount() {
        return ITEM_DELEGATES.size();
    }
}
