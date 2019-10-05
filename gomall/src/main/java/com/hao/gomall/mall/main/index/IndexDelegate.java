package com.hao.gomall.mall.main.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall.mall.R2;
import com.hao.gomall.mall.goods.GoodsInfoActivity;
import com.hao.gomall.mall.util.Constants;
import com.hao.gomall_core.delegates.bottom.BottomItemDelegate;
import com.hao.gomall_core.recycler.home.IStartGoodsInfo;
import com.hao.gomall_core.recycler.home.bean.GoodsBean;
import com.hao.gomall_core.ui.refresh.RefreshHandler;
import com.hao.gomall_core.widget.PullRecyclerView;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.Objects;

import butterknife.BindView;

import static com.hao.gomall_core.recycler.home.adapter.HomeFragmentAdapter.GOODS_BEAN;

public class IndexDelegate extends BottomItemDelegate implements IStartGoodsInfo {
    @BindView(R2.id.rv_index)
    PullRecyclerView rvIndex;
    @BindView(R2.id.refresh_index)
    SwipeRefreshLayout refreshIndex;
    @BindView(R2.id.icon_index_scan)
    IconTextView iconIndexScan;
    @BindView(R2.id.icon_index_message)
    IconTextView iconIndexMessage;
    @BindView(R2.id.toolbar_index)
    Toolbar toolbarIndex;

    private RefreshHandler mRefreshHandler;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = new RefreshHandler(refreshIndex, rvIndex, getContext(), this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        mRefreshHandler.fisrtPage(Constants.HOME_URL);
    }


    private void initRefreshLayout(){
        refreshIndex.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        refreshIndex.setProgressViewOffset(true, 0, 200);
    }

    @Override
    public void startGoodsInfo(GoodsBean goodsBean) {
        Intent intent = new Intent(getContext(), GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goodsBean);
        Objects.requireNonNull(getContext()).startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }


}
