package com.hao.gomall.mall.delegates.main.personal.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall.mall.R2;
import com.hao.gomall.mall.delegates.main.personal.list.ListAdapter;
import com.hao.gomall.mall.delegates.main.personal.list.ListBean;
import com.hao.gomall.mall.delegates.main.personal.profile.setting.NameDelegate;
import com.hao.gomall.mall.util.Constants;
import com.hao.gomall_core.delegates.MallDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class UserProfileDelegate extends MallDelegate {

    @BindView(R2.id.rv_user_profile)
    RecyclerView mRvUserProfile;


    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        ListBean image = new ListBean.Builder()
                .setItemType(ListAdapter.ITEM_AVATAR)
                .setId(1)
                .setImageUrl(Constants.BASE_IMAGE_URL + "/1478850234799.jpg")
                .build();
        ListBean name = new ListBean.Builder()
                .setItemType(ListAdapter.ITEM_NORMAL)
                .setId(2)
                .setText("姓名")
                .setDelegate(new NameDelegate())
                .setValue("未设置")
                .build();

        ListBean gender = new ListBean.Builder()
                .setItemType(ListAdapter.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("未设置")
                .build();

        ListBean birth = new ListBean.Builder()
                .setItemType(ListAdapter.ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setValue("未设置")
                .build();


        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birth);

        mRvUserProfile.setLayoutManager(new LinearLayoutManager(getContext()));
        final ListAdapter adapter = new ListAdapter(data);
        mRvUserProfile.setAdapter(adapter);
        mRvUserProfile.addOnItemTouchListener(new UserProfileClickListener(this));
    }
}
