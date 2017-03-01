package gear.yc.finder.write;

import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.util.Elements;

/**
 * SJQ_ECSHOP_MJ_NEW
 * Created by YichenZ on 2017/3/1 15:46.
 */

public abstract class AbstractWrite<T> {

    protected Elements mElementUtils;//元素相关
    protected boolean isGenerate=false;
    protected TypeSpec typeClass;

    protected T mApiModel;

    public abstract boolean init(Elements elementUtils,RoundEnvironment roundEnv);

    public abstract void writeTo(Filer filer) throws IOException;
}
