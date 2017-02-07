package com.gear.apifinder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * GearApplication
 * 需要把当前annotation标识在Retrofit的API接口上，有几个就标识几个
 * 程序会根据当前的annotation自动生成Retrofit API管理类
 * 如果不做任何标识那么此类就不会被生成
 * Created by YichenZ on 2017/1/17 10:29.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface APIService {
}
