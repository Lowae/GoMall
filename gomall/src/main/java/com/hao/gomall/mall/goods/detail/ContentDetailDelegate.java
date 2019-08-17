package com.hao.gomall.mall.goods.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall_core.delegates.MallDelegate;

public class ContentDetailDelegate extends MallDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_content_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
