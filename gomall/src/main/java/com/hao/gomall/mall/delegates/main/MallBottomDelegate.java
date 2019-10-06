package com.hao.gomall.mall.delegates.main;

import com.hao.gomall.mall.R;
import com.hao.gomall.mall.delegates.main.cart.ShopCartDelegate;
import com.hao.gomall.mall.delegates.main.discover.DiscoverDelegate;
import com.hao.gomall.mall.delegates.main.index.IndexDelegate;
import com.hao.gomall.mall.delegates.main.personal.PersonalDelegate;
import com.hao.gomall.mall.delegates.main.sort.SortDelegate;
import com.hao.gomall_core.delegates.bottom.BaseBottomDelegate;
import com.hao.gomall_core.delegates.bottom.BottomItemBuilder;
import com.hao.gomall_core.delegates.bottom.BottomItemDelegate;
import com.hao.gomall_core.delegates.bottom.BottomTabBean;

import java.util.LinkedHashMap;

public class MallBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(BottomItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return getResources().getColor(R.color.colorAccent);
//        return Color.parseColor("#ffff8800");
    }
}
