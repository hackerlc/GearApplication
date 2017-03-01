package com.gear.apifinder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * SJQ_ECSHOP_MJ_NEW
 * Created by YichenZ on 2017/3/1 15:17.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface APICustomHandleResult {
    //如果为空就不生成
    String handleResult() default "";
}
