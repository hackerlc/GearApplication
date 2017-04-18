package gear.yc.finder.write;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;

import gear.yc.finder.anno.handler.APIRouterHandler;
import gear.yc.finder.anno.handler.APIServiceHandler;
import gear.yc.finder.model.APIRouterModel;
import gear.yc.finder.model.ServiceElementModel;

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

    APIServiceHandler apiSrvHandler;
    List<ServiceElementModel> mServiceElements = new ArrayList<>();
    HashMap<String, List<Method>> apiSrvMap = new HashMap<>();

    public APIRouterWrite() {
        routerHandler = new APIRouterHandler();
        apiSrvHandler = new APIServiceHandler();
    }

    @Override
    public boolean init(Elements elementUtils, RoundEnvironment roundEnv) {
        mElementUtils = elementUtils;
        isGenerate = routerHandler.processorOnAnnotation(roundEnv);
        if (!isGenerate) {
            return isGenerate;
        }
        isGenerate = apiSrvHandler.processorOnAnnotation(roundEnv);
        mServiceElements = apiSrvHandler.getEleModel();//获取所有被annotation标记的类
        //序列化每个Srv annotation class下的方法 key class value method
        //        for(ServiceElementModel element : mServiceElements){
        //            element
        //            List<Method> methods =new ArrayList<>();
        //
        //        }
        //        TypeName typeName = TypeName.get(mServiceElements.get(0)
        //                .getElement().getEnclosedElements().get(0).asType());
        mApiModel = routerHandler.getEleModel();
        Type type[] = new Type[]{Integer.class};
        //        Class clazz = null;
        //        try {
        //            clazz = Class.forName("gear.yc.com.gearapplication.pojo.User");
        //        } catch (ClassNotFoundException e) {
        //            e.printStackTrace();
        //            return false;
        //        }
        //给定方法名称，并且为了不重名以当前类名为后缀
        StringBuffer methodName = new StringBuffer(mServiceElements.get(0)
                .getElement().getEnclosedElements().get(0).getSimpleName())
                .append(mServiceElements.get(0).getElement().getSimpleName());

        //获取返回类型，返回值为string需要处理
        String returnType = String.valueOf(mServiceElements.get(0)
                .getElement().getEnclosedElements().get(0).asType());
        returnType = returnType.substring(returnType.indexOf(")")+1,returnType.length());

        MethodSpec methodSpec = MethodSpec.methodBuilder(methodName.toString())
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .returns(TypeVariableName.get(returnType))
                .addStatement("\nreturn APIServiceManager\n" +
                        "                .getTravelNotesAPI()\n" +
                        "                .getTravelNotesList(query, page)\n" +
                        "                .compose(bindUntilEvent(lifecycleSubject))\n" +
                        "                .subscribeOn(Schedulers.io())\n" +
                        "                .observeOn(AndroidSchedulers.mainThread())\n" +
                        "                .compose(SchedulersHelper.handleResult())")
                .build();

        typeClass = TypeSpec.classBuilder(mApiModel.getClassName())
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodSpec)
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
