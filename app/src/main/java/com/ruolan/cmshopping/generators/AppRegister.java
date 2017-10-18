package com.ruolan.cmshopping.generators;

import com.ruolan.annotations.AppRegisterGenerator;
import com.ruolan.cainiao_core.wechat.templete.AppRegisterTemplete;

/**
 * Created by wuyinlei on 2017/10/18.
 */
@AppRegisterGenerator(
        packageName = "com.ruolan.cmshopping",
        registerTemplete = AppRegisterTemplete.class
)
public interface AppRegister {
}
