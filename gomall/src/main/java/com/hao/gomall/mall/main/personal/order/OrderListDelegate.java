package com.hao.gomall.mall.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall.mall.R2;
import com.hao.gomall.mall.main.personal.PersonalDelegate;
import com.hao.gomall.mall.recycler.converter.MultipleItemEntity;
import com.hao.gomall.mall.util.Constants;
import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.net.RestClient;
import com.hao.gomall_core.net.callback.ISuccess;

import java.util.List;

import butterknife.BindView;

public class OrderListDelegate extends MallDelegate {

    private String mType;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url(Constants.ORDER_LIST_URL)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data = new OrderListDataConverter().setJsonData(response).convert();
                        final OrderRecyclerAdapter adapter = new OrderRecyclerAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
