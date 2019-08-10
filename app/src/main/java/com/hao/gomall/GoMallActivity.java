package com.hao.gomall;

import com.hao.gomall_core.activities.ProxyActivity;
import com.hao.gomall_core.delegates.MallDelegate;

public class GoMallActivity extends ProxyActivity {

    @Override
    public MallDelegate setRootDelegate() {
        return new GoMallDelegate();
    }
}
