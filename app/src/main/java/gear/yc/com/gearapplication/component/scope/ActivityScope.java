package gear.yc.com.gearapplication.component.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 标示Activity的生命周期
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {

}
