package gear.yc.finder.write;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;

import gear.yc.finder.anno.handler.APIRouterHandler;
import gear.yc.finder.model.APIRouterModel;

/**
 * GearApplication
 * Created by YichenZ on 2017/3/1 16:38.
 */

public class APIRouterWrite extends AbstractWrite<APIRouterModel> {
    private static APIRouterWrite instance;

    public static APIRouterWrite getInstance() {
        if (instance == null) {
            synchronized (APIRouterWrite.class) {
                if (instance == null) {
                    instance = new APIRouterWrite();
                }
            }
        }
        return instance;
    }

    APIRouterHandler routerHandler;

    public APIRouterWrite() {
        routerHandler=new APIRouterHandler();
    }

    @Override
    public boolean init(Elements elementUtils, RoundEnvironment roundEnv) {
        mElementUtils = elementUtils;
        isGenerate = routerHandler.processorOnAnnotation(roundEnv);
        if(!isGenerate){
            return isGenerate;
        }

        mApiModel=routerHandler.getEleModel();

        typeClass = TypeSpec.classBuilder(mApiModel.getClassName())
                .addModifiers(Modifier.PUBLIC)
                .build();

        return true;
    }

    @Override
    public void writeTo(Filer filer) throws IOException {
        JavaFile.builder(mApiModel.getPackageName(mElementUtils), typeClass)
                .build()
                .writeTo(filer);
    }
}
