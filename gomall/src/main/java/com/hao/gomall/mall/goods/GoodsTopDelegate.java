package com.hao.gomall.mall.goods;

import com.hao.gomall.mall.goods.comment.CommentDelegate;
import com.hao.gomall.mall.goods.info.GoodsInfoDelegate;
import com.hao.gomall_core.delegates.MallDelegate;
import com.hao.gomall_core.delegates.goodsinfo.BaseGoodsInfoDelegate;

import java.util.LinkedHashMap;

public class GoodsTopDelegate extends BaseGoodsInfoDelegate {


    @Override
    public LinkedHashMap<String, MallDelegate> setItems() {
        final LinkedHashMap<String, MallDelegate> items = new LinkedHashMap<>();
        GoodsInfoDelegate goodsInfoDelegate = new GoodsInfoDelegate();
        goodsInfoDelegate.setArguments(getArguments());
        CommentDelegate commentDelegate = new CommentDelegate();
        commentDelegate.setArguments(getArguments());
        goodsInfoDelegate.setArguments(getArguments());
        items.put("商品", goodsInfoDelegate);
        items.put("评价", commentDelegate);
        return items;
    }

}
