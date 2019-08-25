package com.hao.gomall.mall.goods;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hao.gomall_core.activities.ProxyActivity;
import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.recycler.home.adapter.HomeFragmentAdapter;
import com.hao.gomall_core.recycler.home.bean.GoodsBean;

public class GoodsInfoActivity extends ProxyActivity {

    private GoodsBean goodsBean;
    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        goodsBean = (GoodsBean) getIntent().getSerializableExtra(HomeFragmentAdapter.GOODS_BEAN);
        bundle = new Bundle();
        bundle.putSerializable(HomeFragmentAdapter.GOODS_BEAN, goodsBean);
        super.onCreate(savedInstanceState);
    }

    @Override
    public MallDelegate setRootDelegate() {
        GoodsTopDelegate delegate = new GoodsTopDelegate();
        delegate.setArguments(bundle);
        return delegate;
    }

}
