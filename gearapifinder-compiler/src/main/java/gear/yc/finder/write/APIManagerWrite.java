package gear.yc.finder.write;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;

import gear.yc.finder.anno.handler.APIManagerHandler;
import gear.yc.finder.anno.handler.APIServiceHandler;
import gear.yc.finder.model.APIManagerElementModel;
import gear.yc.finder.model.ServiceElementModel;
import gear.yc.finder.utils.MtdMark;
import gear.yc.finder.utils.StrHandling;

/**
 * GearApplication
 * Created by YichenZ on 2017/1/22 09:16.
 */

public class APIManagerWrite extends AbstractWrite<APIManagerElementModel>{
    private static APIManagerWrite instance;

    public static APIManagerWrite getInstance() {
        if(instance==null){
            synchronized (APIManagerWrite.class){
                if(instance==null){
                    instance=new APIManagerWrite();
                }
            }
        }
        return instance;
    }
    APIManagerHandler apiHandler;
    APIServiceHandler apiSrvHandler;

    List<ServiceElementModel> mServiceElements=new ArrayList<>();

    //Field list
    List<FieldSpec> fieldSpecs =new ArrayList<>();
    //Method list
    List<MethodSpec> mtdSpecs =new ArrayList<>();

    public APIManagerWrite(){
        apiHandler = new APIManagerHandler();
        apiSrvHandler = new APIServiceHandler();
    }

    @Override
    public boolean init(Elements elementUtils,RoundEnvironment roundEnv){
        mElementUtils=elementUtils;
        isGenerate=apiHandler.processorOnAnnotation(roundEnv);
        if(!isGenerate){
            return isGenerate;
        }
        isGenerate=apiSrvHandler.processorOnAnnotation(roundEnv);
        if(!isGenerate){
            return isGenerate;
        }

        mServiceElements=apiSrvHandler.getEleModel();
        mApiModel =apiHandler.getEleModel();

        for (ServiceElementModel sModel : mServiceElements) {
            FieldSpec fieldSpec = FieldSpec.builder(sModel.getTypeName(),sModel.getFieldName4Letter())
                    .addModifiers(Modifier.PRIVATE,Modifier.STATIC)
                    .build();
            fieldSpecs.add(fieldSpec);

            //Construct Method
            MethodSpec mtdSpec = MethodSpec.methodBuilder(StrHandling.getMtdStr(MtdMark.GET,sModel.getFieldName()))
                    .addModifiers(Modifier.PUBLIC,Modifier.STATIC)
                    .returns(sModel.getTypeName())
                    .addStatement(StrHandling
                                    .getReturnStr(sModel.getFieldName4Letter(), mApiModel.getAnnotation())
                            , mApiModel.getTypeName(),sModel.getTypeName())
                    .build();
            mtdSpecs.add(mtdSpec);
        }
        //Construct Class
        typeClass = TypeSpec.classBuilder(mApiModel.getClassName())
                .addModifiers(Modifier.PUBLIC)
                .addFields(fieldSpecs)
                .addMethods(mtdSpecs)
                .build();

        return isGenerate;
    }

    @Override
    public void writeTo(Filer filer) throws IOException {
        JavaFile.builder(mApiModel.getPackageName(mElementUtils),typeClass)
                .build()
                .writeTo(filer);
    }
}
