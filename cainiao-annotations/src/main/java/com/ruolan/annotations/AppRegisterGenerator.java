package com.ruolan.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wuyinlei on 2017/10/18.
 */
@Target(ElementType.TYPE)  //类注解
@Retention(RetentionPolicy.SOURCE)  //在源码编译阶段
public @interface AppRegisterGenerator {

    String packageName();

    Class<?> registerTemplete();

}
