package com.hao.gomall.generators;

import com.hao.gomall_annotations.PayEntryGenerator;
import com.hao.gomall_core.activities.wx.templates.WXPayEntryTemplate;

@PayEntryGenerator(packageName = "com.hao.gomall", payEntryTemplate = WXPayEntryTemplate.class)
public interface WeChatPayEntry {

}
