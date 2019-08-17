package com.hao.gomall.mall.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.delegates.bottom.BottomItemDelegate;
import com.hao.gomall_core.delegates.web.WebDelegateImpl;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class DiscoverDelegate extends BottomItemDelegate {



    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("https://h5.m.taobao.com");
        delegate.setTopDelegate((MallDelegate) getParentFragment());
        loadRootFragment(R.id.fl_web_discovery_container, delegate);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
