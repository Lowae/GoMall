package com.hao.gomall.mall.delegates.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall.mall.util.Constants;
import com.hao.gomall_core.delegates.bottom.BottomItemDelegate;
import com.hao.gomall_core.net.RestClient;
import com.hao.gomall_core.net.callback.ISuccess;
import com.hao.gomall_core.utils.MallLogger;

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess {

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url(Constants.SHOP_CART_URL)
                .success(this)
                .build()
                .get();

    }

    @Override
    public void onSuccess(String response) {
        MallLogger.d("shop_cart", response);
    }
}
