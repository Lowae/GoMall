package com.hao.gomall_core.delegates.web.event;

import com.hao.gomall_core.utils.MallLogger;

public class UndefinedEvent extends Event {

    @Override
    public String execute(String params) {
        MallLogger.e("Undefine Event", params);
        return null;
    }
}
