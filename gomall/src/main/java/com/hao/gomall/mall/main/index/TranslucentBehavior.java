package com.hao.gomall.mall.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.hao.gomall.mall.R;
import com.hao.gomall_core.recycler.RGBValue;

public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    //顶部距离
    private int mDistanceY = 0;
    //变化速率
    private static final int CHANGE_SPEED = 3;
    //颜色变化
    private final RGBValue RGB_VALUE = RGBValue.create(255, 124, 2);

    public TranslucentBehavior() {
    }

    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull Toolbar child, @NonNull View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return type == ViewCompat.TYPE_TOUCH;
    }

    /**
     *
     * @param child 依赖的对象
     * @param target 被依赖的对象
     */
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        if (type == ViewCompat.TYPE_TOUCH){
            mDistanceY += dy;
            //toolbar高度
            final int targetHeight = child.getBottom();

            //当滑动时
            if (mDistanceY > 0 && mDistanceY <= targetHeight){
                final float scale = (float) (mDistanceY / 1.0 * targetHeight);
                final float alpha = scale * 255;
                child.setBackgroundColor(Color.argb((int) alpha, RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
            }else if (mDistanceY > targetHeight){
                child.setBackgroundColor(Color.rgb(RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
            }
        }
    }
}
