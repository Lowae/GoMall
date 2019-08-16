package com.hao.gomall_core.delegates.bottom;

import android.widget.Toast;

import com.hao.gomall_core.R;
import com.hao.gomall_core.app.Mall;
import com.hao.gomall_core.delegates.MallDelegate;

public abstract class BottomItemDelegate extends MallDelegate {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Mall.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
