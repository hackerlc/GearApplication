package com.gear.apifinder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * GearApplication
 * 需要标识在你应用中返回唯一的Retrofit引用类上，只能标识在主app模块中
 * 不能在Library上标识，annotation会无法识别
 * 如果不对任何类做标识那么程序将不会生成Retrofit API管理类
 * Created by YichenZ on 2017/1/17 10:31.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface APIManager {
    //class name is value
    String value() default "APIServiceManager";
    //is singleton model
    boolean isSingleton() default true;
    //return retrofit type for method name
    String retrofit() default "getRetrofit";
}
