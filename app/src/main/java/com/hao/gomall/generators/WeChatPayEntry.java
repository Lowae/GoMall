package com.hao.gomall.generators;

import com.hao.gomall_annotations.PayEntryGenerator;
import com.hao.gomall_core.weChat.templates.WXPayEntryTemplate;

@PayEntryGenerator(packageName = "com.hao.gomall", payEntryTemplate = WXPayEntryTemplate.class)
public interface WeChatPayEntry {

}
