package com.hao.gomall_core.ui.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.hao.gomall_core.app.Mall;
import com.hao.gomall_core.net.RestClient;
import com.hao.gomall_core.net.callback.ISuccess;
import com.hao.gomall_core.recycler.home.IStartGoodsInfo;
import com.hao.gomall_core.recycler.home.adapter.HomeFragmentAdapter;
import com.hao.gomall_core.recycler.home.bean.ResultBeanData;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout refreshLayout;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private IStartGoodsInfo iStartGoodsInfo;


    public RefreshHandler(SwipeRefreshLayout refreshLayout, RecyclerView rvIndex, Context context, IStartGoodsInfo iStartGoodsInfo) {
        this.refreshLayout = refreshLayout;
        mRecyclerView = rvIndex;
        mContext = context;
        refreshLayout.setOnRefreshListener(this);
        this.iStartGoodsInfo = iStartGoodsInfo;
    }

    private void refresh(){
        refreshLayout.setRefreshing(true);
        Mall.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    public void fisrtPage(String url){
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        ResultBeanData resultBeanData = JSON.parseObject(response, ResultBeanData.class);
                        ResultBeanData.ResultBean resultBean = resultBeanData.getResult();
                        HomeFragmentAdapter adapter = new HomeFragmentAdapter(mContext, resultBean, iStartGoodsInfo);
                        mRecyclerView.setAdapter(adapter);
                        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 1));
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
