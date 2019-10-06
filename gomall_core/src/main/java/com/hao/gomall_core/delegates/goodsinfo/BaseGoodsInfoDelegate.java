package com.hao.gomall_core.delegates.goodsinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hao.gomall_core.R;
import com.hao.gomall_core.R2;
import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.delegates.goodsinfo.adapter.GoodsPageAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;

public abstract class BaseGoodsInfoDelegate extends MallDelegate {

    private final String TAG = getClass().getSimpleName();

    private final ArrayList<MallDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final ArrayList<String> TAB_BEANS = new ArrayList<>();



    @BindView(R2.id.goods_tabs)
    TabLayout tabLayout;
    @BindView(R2.id.goods_delegate_container)
    ViewPager viewPager;


    private final LinkedHashMap<String, MallDelegate> ITEMS = new LinkedHashMap<>();

    public abstract LinkedHashMap<String, MallDelegate> setItems();



    @Override
    public Object setLayout() {
        return R.layout.delegate_goodsinfo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setItems();
        final LinkedHashMap<String, MallDelegate> items = setItems();
        ITEMS.putAll(items);
        for (Map.Entry<String, MallDelegate> item : ITEMS.entrySet()) {
            TAB_BEANS.add(item.getKey());
            ITEM_DELEGATES.add(item.getValue());
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        GoodsPageAdapter adapter = new GoodsPageAdapter(getFragmentManager(), TAB_BEANS, ITEM_DELEGATES);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

}
