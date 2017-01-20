package gear.yc.finder;

import com.gear.apifinder.annotation.APIManager;
import com.gear.apifinder.annotation.APIService;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import gear.yc.finder.anno.handler.APIManagerHandler;
import gear.yc.finder.anno.handler.APIServiceHandler;
import gear.yc.finder.model.APIManagerElementModel;
import gear.yc.finder.model.ServiceElementModel;
import gear.yc.finder.utils.MtdMark;
import gear.yc.finder.utils.StrHandling;


@AutoService(Processor.class)
public class FinderProcesser extends AbstractProcessor {
    private Filer mFiler;//文件相关
    private Elements mElementUtils;//元素相关
    private Messager mMessager;//日志相关

    private boolean isGenerate=true;

    APIManagerHandler apiHandler;
    APIServiceHandler apiSrvHandler;

    List<ServiceElementModel> mServiceElements=new ArrayList<>();
    APIManagerElementModel apiModel;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler=processingEnv.getFiler();
        mElementUtils=processingEnv.getElementUtils();
        mMessager=processingEnv.getMessager();
        apiHandler=new APIManagerHandler();
        apiSrvHandler=new APIServiceHandler();
    }

    /**
     * 指定哪些注解应该被注解处理器注册
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types=new LinkedHashSet<>();
        types.add(APIManager.class.getCanonicalName());
        types.add(APIService.class.getCanonicalName());
        return Collections.unmodifiableSet(types);
    }

    /**
     * 指定使用java版本
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        isGenerate=apiHandler.processorOnAnnotation(roundEnv);
        isGenerate=apiSrvHandler.processorOnAnnotation(roundEnv);

        if(!isGenerate){
            return isGenerate;
        }

        mServiceElements=apiSrvHandler.getEleModel();
        apiModel=apiHandler.getEleModel();

        //Field list
        List<FieldSpec> fieldSpecs =new ArrayList<>();
        //Method list
        List<MethodSpec> mtdSpecs =new ArrayList<>();

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
                            .getReturnStr(sModel.getFieldName4Letter(),apiModel.getAnnotation())
                            ,apiModel.getTypeName(),sModel.getTypeName())
                    .build();
            mtdSpecs.add(mtdSpec);
        }
        //Construct Class
        TypeSpec typeClass = TypeSpec.classBuilder(apiModel.getClassName())
                .addModifiers(Modifier.PUBLIC)
                .addMethods(mtdSpecs)//添加方法
                .addFields(fieldSpecs)
                .build();
        //Construct java
        JavaFile javaFile =JavaFile
                .builder(apiModel.getPackageName(mElementUtils),typeClass)
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
