package com.hao.gomall.mall.delegates.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall_core.delegates.bottom.BottomItemDelegate;

public class SortDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        assert getFragmentManager() != null;
        getSupportDelegate().loadRootFragment(R.id.fl_type, new ListDelegate());
//        getFragmentManager().beginTransaction().add(R.id.fl_type, new ListDelegate()).commit();
    }
}
