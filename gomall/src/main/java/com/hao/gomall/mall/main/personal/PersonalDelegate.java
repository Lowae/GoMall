package com.hao.gomall.mall.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall.mall.R2;
import com.hao.gomall.mall.main.personal.list.ListAdapter;
import com.hao.gomall.mall.main.personal.list.ListBean;
import com.hao.gomall_core.delegates.bottom.BottomItemDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PersonalDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSetting;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        ListBean address = new ListBean.Builder()
                .setItemType(20)
                .setId(1)
                .setText("收获地址")
                .build();
        ListBean system = new ListBean.Builder()
                .setItemType(20)
                .setId(2)
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        mRvSetting.setLayoutManager(new LinearLayoutManager(getContext()));
        final ListAdapter adapter = new ListAdapter(data);
        mRvSetting.setAdapter(adapter);
    }
}
