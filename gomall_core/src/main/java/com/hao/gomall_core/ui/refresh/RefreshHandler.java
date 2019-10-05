package com.hao.gomall_core.ui.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.hao.gomall_core.app.Mall;
import com.hao.gomall_core.net.rx.RxRestClient;
import com.hao.gomall_core.recycler.home.IStartGoodsInfo;
import com.hao.gomall_core.recycler.home.adapter.HomeFragmentAdapter;
import com.hao.gomall_core.recycler.home.bean.ResultBeanData;
import com.hao.gomall_core.recycler.home.decoration.HotRecyclerViewDecoration;
import com.hao.gomall_core.utils.Constants;
import com.hao.gomall_core.utils.MallLogger;
import com.hao.gomall_core.widget.PullRecyclerView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout refreshLayout;
    private PullRecyclerView mRecyclerView;
    private Context mContext;
    private IStartGoodsInfo iStartGoodsInfo;

    private HomeFragmentAdapter adapter;

    private ResultBeanData resultBeanData;

    private int page = 1;

    private Observer<String> mObserver = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(String s) {
            adapter.hotInfoBeans.addAll(JSON.parseArray(s, ResultBeanData.ResultBean.HotInfoBean.class));
            page++;
            mRecyclerView.onRefreshComplete(true);
        }

        @Override
        public void onError(Throwable e) {
            MallLogger.e("HomeFragmentadapter", e.getMessage());
            mRecyclerView.onRefreshComplete(false);
        }

        @Override
        public void onComplete() {

        }
    };

    public RefreshHandler(SwipeRefreshLayout refreshLayout, PullRecyclerView rvIndex, Context context, IStartGoodsInfo iStartGoodsInfo) {
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
                        resultBeanData = JSON.parseObject(s, ResultBeanData.class);
                        adapter = new HomeFragmentAdapter(mContext, resultBeanData.getResult(), iStartGoodsInfo);
                        mRecyclerView.setAdapter(adapter);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
                        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int i) {
                                if (adapter.getItemViewType(i) == HomeFragmentAdapter.HOT){
                                    return 1;
                                }else {
                                    return 2;
                                }
                            }
                        });
                        mRecyclerView.setLayoutManager(gridLayoutManager);
                        mRecyclerView.setOnPullRefreshListener(new PullRecyclerView.PullRecyclerRefreshListener() {
                            @Override
                            public void loadMore() {
                                RxRestClient.builder().url(String.format(Constants.HOTINFOMORE, page))
                                        .build()
                                        .get()
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(mObserver);
                            }

                            @Override
                            public void onRefresh() {

                            }
                        });
                        mRecyclerView.addItemDecoration(new HotRecyclerViewDecoration());
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
