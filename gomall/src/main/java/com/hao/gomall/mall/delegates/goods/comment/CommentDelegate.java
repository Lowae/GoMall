package com.hao.gomall.mall.delegates.goods.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall_core.delegates.MallDelegate;

public class CommentDelegate extends MallDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
