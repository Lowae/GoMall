package com.hao.gomall.mall.delegates.goods.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hao.gomall.mall.R;
import com.hao.gomall.mall.R2;
import com.hao.gomall.mall.delegates.goods.info.adapter.GoodsInfoDetailAdapter;
import com.hao.gomall.mall.ui.page.PageContainer;
import com.hao.gomall.mall.util.Constants;
import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.recycler.home.adapter.HomeFragmentAdapter;
import com.hao.gomall_core.recycler.home.bean.GoodsBean;

import java.util.LinkedHashMap;

import butterknife.BindView;

public class GoodsInfoDelegate extends MallDelegate {

    @BindView(R2.id.iv_good_info_image)
    ImageView ivGoodInfoImage;
    @BindView(R2.id.tv_good_info_name)
    TextView tvGoodInfoName;
    @BindView(R2.id.tv_good_info_desc)
    TextView tvGoodInfoDesc;
    @BindView(R2.id.tv_good_info_price)
    TextView tvGoodInfoPrice;
    @BindView(R2.id.tv_good_info_store)
    TextView tvGoodInfoStore;
    @BindView(R2.id.tv_good_info_style)
    TextView tvGoodInfoStyle;
    @BindView(R2.id.page_container)
    PageContainer pageContainer;

    @BindView(R2.id.tab_goods_info)
    TabLayout mGoodsTabLayout;
    @BindView(R2.id.vp_goods_info)
    ViewPager mGoodsViewPager;

    private GoodsBean goodsBean;
    public final String[] tabTitle = new String[]{"图文详情","规格参数","售后保障"};


    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_info;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        goodsBean = (GoodsBean) getArguments().getSerializable(HomeFragmentAdapter.GOODS_BEAN);
        Glide.with(getContext()).load(Constants.BASE_IMAGE_URL + goodsBean.getFigure()).into(ivGoodInfoImage);
        tvGoodInfoName.setText(goodsBean.getName());
        tvGoodInfoPrice.setText(String.format("￥%s", goodsBean.getCover_price()));

        initGoodsInfo();
    }

    private void initGoodsInfo() {
        final LinkedHashMap<String, Fragment> detailItems = new LinkedHashMap<>();
        detailItems.put(tabTitle[0], new GoodsInfoDetailDeleagte());
        detailItems.put(tabTitle[1], new GoodsInfoParamDelegate());
        detailItems.put(tabTitle[2], new GoodsInfoPromiseDelegate());

        GoodsInfoDetailAdapter adapter = new GoodsInfoDetailAdapter(getFragmentManager(), detailItems, tabTitle);
        mGoodsViewPager.setOffscreenPageLimit(3);
        mGoodsViewPager.setAdapter(adapter);
        mGoodsTabLayout.setupWithViewPager(mGoodsViewPager);
    }

    public void scrollToBottom(){
        pageContainer.scrollToBottom();
    }

    public void scrollToTop(){
        pageContainer.backToTop();
    }
}
