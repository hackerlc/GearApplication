package gear.yc.finder.anno.handler;

import javax.annotation.processing.RoundEnvironment;

/**
 * GearApplication
 * Created by YichenZ on 2017/1/20 17:02.
 */

public abstract class AbstractAnnotationHandler<T> {
    T mEleModel;
    public abstract boolean processorOnAnnotation(RoundEnvironment environment);

    public T getEleModel() {
        return mEleModel;
    }

}
