package com.hao.gomall.mall.delegates.main.personal.order;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hao.gomall.mall.R;
import com.hao.gomall.mall.recycler.MultipleRecyclerAdapter;
import com.hao.gomall.mall.recycler.MultipleViewHolder;
import com.hao.gomall.mall.recycler.converter.MultipleFields;
import com.hao.gomall.mall.recycler.converter.MultipleItemEntity;
import com.hao.gomall.mall.util.Constants;

import java.util.List;

public class OrderRecyclerAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .centerCrop()
            .dontAnimate();


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public OrderRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);
    }

    @Override
    protected void convert(@NonNull MultipleViewHolder helper, MultipleItemEntity item) {
        super.convert(helper, item);
        if (helper.getItemViewType() == OrderListItemType.ITEM_ORDER_LIST) {
            final AppCompatImageView imageView = helper.getView(R.id.image_order_list);
            final AppCompatTextView title = helper.getView(R.id.tv_order_list_title);
            final AppCompatTextView price = helper.getView(R.id.tv_order_list_price);
            final AppCompatTextView time = helper.getView(R.id.tv_order_list_time);

            final String titleVal = item.getField(MultipleFields.TITLE);
            final String timeVal = item.getField(OrderItemFields.TIME);
            final double priceVal = item.getField(OrderItemFields.PRICE);
            final String imageUrl = Constants.BASE_IMAGE_URL + item.getField(MultipleFields.IMAGE_URL);

            Glide.with(mContext)
                    .load(imageUrl)
                    .apply(OPTIONS)
                    .into(imageView);

            title.setText(titleVal);
            price.setText(String.format("价格：%s", String.valueOf(priceVal)));
            time.setText(String.format("时间：%s", timeVal));
        }
    }
}
