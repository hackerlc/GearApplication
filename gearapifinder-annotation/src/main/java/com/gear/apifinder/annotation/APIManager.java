package com.gear.apifinder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * GearApplication
 * Created by YichenZ on 2017/1/17 10:31.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface APIManager {
}
