package com.hao.gomall.generators;


import com.hao.gomall_annotations.EntryGenerator;
import com.hao.gomall_core.weChat.templates.WXEntryTemplate;

@EntryGenerator(packageName = "com.hao.gomall", entryTemplate = WXEntryTemplate.class)
public interface WeChatEntry {

}
