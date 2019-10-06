package com.hao.gomall.mall.delegates.main.personal.list;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hao.gomall.mall.R;

import java.util.List;

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    public static final int ITEM_NORMAL = 20;
    public static final int ITEM_AVATAR = 21;

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ITEM_AVATAR, R.layout.arrow_item_avatar);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ListBean listBean) {
        switch (baseViewHolder.getItemViewType()){
            case ITEM_NORMAL:
                baseViewHolder.setText(R.id.tv_arrow_text, listBean.getmText());
                baseViewHolder.setText(R.id.tv_arrow_value, listBean.getmValue());
                break;
            case ITEM_AVATAR:
                Glide.with(mContext).load(listBean.getmImageUrl()).into((ImageView) baseViewHolder.getView(R.id.img_arrow_avatar));
                break;
            default:
                break;
        }
    }
}
