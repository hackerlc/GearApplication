package com.gear.apifinder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * SJQ_ECSHOP_MJ_NEW
 * Created by YichenZ on 2017/3/1 14:39.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface APIRouter {
    //class name
    String value() default "APIRouter";
    //如果设置为空那么就不生成rx生命管理，否则按照输入的生成
    String rxLifecycle() default "";

}
