package com.ruolan.cmshopping.generators;

import com.ruolan.annotations.EntryGenerator;
import com.ruolan.cainiao_core.wechat.templete.WXEntryTemplete;

/**
 * Created by wuyinlei on 2017/10/18.
 */
@EntryGenerator(
        packageName = "com.ruolan.cmshopping",
        entryTemplete = WXEntryTemplete.class
)
public interface WeChatEntry {
}
