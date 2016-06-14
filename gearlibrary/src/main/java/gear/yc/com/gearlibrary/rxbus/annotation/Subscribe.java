package gear.yc.com.gearlibrary.rxbus.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import gear.yc.com.gearlibrary.rxbus.thread.ThreadMode;

/**
 * Created by Android on 2016/6/8.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {
    String tag() default "def_tag";

    ThreadMode thread() default ThreadMode.MAIN_THREAD;
}
