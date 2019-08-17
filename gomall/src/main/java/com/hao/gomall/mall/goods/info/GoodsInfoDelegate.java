package com.hao.gomall.mall.goods.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hao.gomall.mall.R;
import com.hao.gomall.mall.R2;
import com.hao.gomall.mall.ui.page.Page;
import com.hao.gomall.mall.ui.page.PageContainer;
import com.hao.gomall.mall.util.Constants;
import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.delegates.web.WebDelegateImpl;
import com.hao.gomall_core.recycler.home.adapter.HomeFragmentAdapter;
import com.hao.gomall_core.recycler.home.bean.GoodsBean;

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
    @BindView(R2.id.pageOne)
    Page pageOne;
    @BindView(R2.id.page_container)
    PageContainer container;

    private GoodsBean goodsBean;

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
        final WebDelegateImpl delegate = WebDelegateImpl.create("http://m.baidu.com");
        delegate.setTopDelegate((MallDelegate) getParentFragment());
        loadRootFragment(R.id.ll_web_container, delegate);
    }
}
