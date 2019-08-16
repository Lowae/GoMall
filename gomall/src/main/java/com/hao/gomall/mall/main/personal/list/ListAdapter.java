package com.hao.gomall.mall.main.personal.list;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hao.gomall.mall.R;

import java.util.List;

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    private static final int ITEM_NORMAL = 20;

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ITEM_NORMAL, R.layout.arrow_item_layout);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ListBean listBean) {
        switch (baseViewHolder.getItemViewType()){
            case 20:
                baseViewHolder.setText(R.id.tv_arrow_text, listBean.getmText());
                baseViewHolder.setText(R.id.tv_arrow_value, listBean.getmValue());
                break;
            default:
                break;
        }
    }
}
