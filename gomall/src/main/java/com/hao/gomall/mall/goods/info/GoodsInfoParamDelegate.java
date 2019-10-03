package com.hao.gomall.mall.goods.info;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall_core.delegates.goodsinfo.BaseGoodsDetailDelegate;

public class GoodsInfoParamDelegate extends BaseGoodsDetailDelegate {



    @Override
    public Object setLayout() {
        return R.layout.delegate_goodsinfoparam;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
