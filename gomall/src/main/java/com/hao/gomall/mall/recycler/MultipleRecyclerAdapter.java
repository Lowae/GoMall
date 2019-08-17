package com.hao.gomall.mall.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.widget.ImageView;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hao.gomall.mall.R;
import com.hao.gomall.mall.recycler.converter.JsonDataConverter;
import com.hao.gomall.mall.recycler.converter.MultipleFields;
import com.hao.gomall.mall.recycler.converter.MultipleItemEntity;
import com.hao.gomall.mall.ui.BannerHolderCreator;

import java.util.ArrayList;
import java.util.List;

public class MultipleRecyclerAdapter extends
        BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup,
        OnItemClickListener {


    //确保初始化一次Banner，防止重复Item加载
    private boolean mIsInitBanner = false;
    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .dontAnimate();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }


    @Override
    protected void convert(@NonNull MultipleViewHolder helper, MultipleItemEntity item) {
        String text = null;
        String imageUrl = null;

        switch (helper.getItemViewType()){
            case ItemType.TEXT:
                text = item.getField(MultipleFields.TEXT);
                helper.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = item.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_single));
                break;
            case ItemType.TEXT_IMAGE:
                text = item.getField(MultipleFields.TEXT);
                imageUrl = item.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_multiple));
                helper.setText(R.id.tv_multiple, text);
                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    final ArrayList<String> bannerImages;
                    bannerImages = item.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = helper.getView(R.id.banner_recycler_item);
                    setDefaultBannerStyle(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }
                break;
            default:
                break;
        }
    }


    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(JsonDataConverter converter) {
        return new MultipleRecyclerAdapter(converter.convert());
    }

    public void refresh(List<MultipleItemEntity> data) {
        getData().clear();
        setNewData(data);
        notifyDataSetChanged();
    }

    private void init() {
        //设置不同的item布局
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    private void setDefaultBannerStyle(ConvenientBanner<String> convenientBanner,
                                       ArrayList<String> banners,
                                       com.bigkoo.convenientbanner.listener.OnItemClickListener clickListener){
        convenientBanner.setPages(new BannerHolderCreator(), banners)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }

    @Override
    public void onItemClick(int position) {

    }
}
