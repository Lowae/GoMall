package com.hao.gomall.mall.main.personal.profile.setting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall_core.delegates.MallDelegate;

/**
 * Created by 傅令杰
 */

public class NameDelegate extends MallDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
