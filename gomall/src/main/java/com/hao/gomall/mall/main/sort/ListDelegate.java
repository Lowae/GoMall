package com.hao.gomall.mall.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.hao.gomall.mall.R;
import com.hao.gomall.mall.R2;
import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.net.RestClient;
import com.hao.gomall_core.net.callback.ISuccess;
import com.hao.gomall_core.recycler.type.adapter.TypeLeftAdapter;
import com.hao.gomall_core.recycler.type.adapter.TypeRightAdapter;
import com.hao.gomall_core.recycler.type.bean.TypeBean;
import com.hao.gomall_core.utils.Constants;

import butterknife.BindView;

public class ListDelegate extends MallDelegate {

    @BindView(R2.id.lv_left)
    ListView lv_left;
    @BindView(R2.id.rv_right)
    RecyclerView rv_right;

    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL, Constants.GAME_URL};

    private TypeLeftAdapter leftAdapter;
    private boolean isFirst = true;

    @Override
    public Object setLayout() {
        return R.layout.delegate_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        getDataFromNet(urls[0]);
    }

    public void getDataFromNet(String url){
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        TypeBean typeBean = JSON.parseObject(response, TypeBean.class);
                        if (isFirst) {
                            leftAdapter = new TypeLeftAdapter(getContext());
                            if (lv_left != null){
                                lv_left.setAdapter(leftAdapter);
                            }
                        }
                        initListener(leftAdapter);
                        //解析右边数据
                        TypeRightAdapter rightAdapter = new TypeRightAdapter(getContext(), typeBean.getResult());
                        rv_right.setAdapter(rightAdapter);

                        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);

                        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int position) {
                                if (position == 0) {
                                    return 3;
                                } else {
                                    return 1;
                                }
                            }
                        });

                        rv_right.setLayoutManager(manager);
                    }
                })
                .build()
                .get();
    }

    private void initListener(final TypeLeftAdapter adapter) {

        //点击监听
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                adapter.changeSelected(position);//刷新

                if (position != 0) {
                    isFirst = false;
                }

                getDataFromNet(urls[position]);

                leftAdapter.notifyDataSetChanged();
            }
        });

        //选中监听
        lv_left.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.changeSelected(position);//刷新
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
