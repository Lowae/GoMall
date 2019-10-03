package com.hao.gomall.mall.goods.info;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.delegates.goodsinfo.BaseGoodsDetailDelegate;
import com.hao.gomall_core.delegates.web.WebDelegateImpl;
import com.hao.gomall_core.utils.MallLogger;

public class GoodsInfoDetailDeleagte extends BaseGoodsDetailDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_goodinfomore;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        MallLogger.d("Web!!!");
        final WebDelegateImpl delegate = WebDelegateImpl.create("http://m.baidu.com");
        delegate.setTopDelegate((MallDelegate) getParentFragment());
        getSupportDelegate().loadRootFragment(R.id.fl_goodsInfoMore, delegate);
    }
}
