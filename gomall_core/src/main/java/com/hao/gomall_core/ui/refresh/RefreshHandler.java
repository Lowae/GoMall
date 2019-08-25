package com.hao.gomall_core.ui.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.hao.gomall_core.app.Mall;
import com.hao.gomall_core.net.RestClient;
import com.hao.gomall_core.net.callback.ISuccess;
import com.hao.gomall_core.net.rx.RxRestClient;
import com.hao.gomall_core.recycler.home.IStartGoodsInfo;
import com.hao.gomall_core.recycler.home.adapter.HomeFragmentAdapter;
import com.hao.gomall_core.recycler.home.bean.ResultBeanData;
import com.hao.gomall_core.utils.Constants;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        }, 1000);
    }

    public void fisrtPage(String url){
        RxRestClient.builder().url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        ResultBeanData resultBeanData = JSON.parseObject(s, ResultBeanData.class);
                        ResultBeanData.ResultBean resultBean = resultBeanData.getResult();
                        HomeFragmentAdapter adapter = new HomeFragmentAdapter(mContext, resultBean, iStartGoodsInfo);
                        mRecyclerView.setAdapter(adapter);
                        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 1));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("错误", "网络请求错误", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void onRefresh() {
        refresh();
    }
}
