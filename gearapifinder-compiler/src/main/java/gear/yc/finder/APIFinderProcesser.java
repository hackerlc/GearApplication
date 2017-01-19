package gear.yc.finder;

import com.gear.apifinder.annotation.APIManager;
import com.gear.apifinder.annotation.APIService;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
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
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;


@AutoService(Processor.class)
public class APIFinderProcesser extends AbstractProcessor {
    final String CLASS_NAME="APIServiceManager";
    private Filer mFiler;//文件相关
    private Elements mElementUtils;//元素相关
    private Messager mMessager;//日志相关
    private boolean isGenerate=true;
    List<Element> mServiceElements=new ArrayList<>();
    List<Element> mManagerElements =new ArrayList<>();



    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler=processingEnv.getFiler();
        mElementUtils=processingEnv.getElementUtils();
        mMessager=processingEnv.getMessager();
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
        return types;
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
        if(!isGenerate){
            return isGenerate;
        }
        //have a @APIService annotation
        processOnAPIService(roundEnv);
        processOnManager(roundEnv);

        //Field list
        List<FieldSpec> fieldSpecs =new ArrayList<>();
        //Method list
        List<MethodSpec> methodSpecs =new ArrayList<>();
        for (int i=0;i<mServiceElements.size();i++) {
            Element element =mServiceElements.get(i);
            TypeName typeName =TypeName.get(element.asType());
            String fieldName=firstUpperToLetter(element.getSimpleName().toString());
            FieldSpec fieldSpec = FieldSpec.builder(typeName,fieldName)
                    .addModifiers(Modifier.PRIVATE,Modifier.STATIC)
                    .build();
            fieldSpecs.add(fieldSpec);

            TypeName typename4Manager=TypeName.get(mManagerElements.get(0).asType());
            //Construct Method
            MethodSpec methodSpec = MethodSpec.methodBuilder("get"+element.getSimpleName().toString())
                    .addModifiers(Modifier.PUBLIC,Modifier.STATIC)
                    .returns(typeName)
                    .addStatement("return "+fieldName+"==null ?\n" +
                            "        $T.getInstance().getRetrofit().create($T.class)\n" +
                            "        :"+fieldName,typename4Manager,typeName)
                    .build();
            methodSpecs.add(methodSpec);
        }

        //Construct Class
        TypeSpec typeClass = TypeSpec.classBuilder(CLASS_NAME)
                .addModifiers(Modifier.PUBLIC)
                .addMethods(methodSpecs)//添加方法
                .addFields(fieldSpecs)
                .build();
        //Construct java
        String packageName=mElementUtils.getPackageOf(mManagerElements.get(0)).getQualifiedName().toString();
        JavaFile javaFile =JavaFile
                .builder(packageName,typeClass)
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void processOnAPIService(RoundEnvironment environment){
        for (Element element : environment.getElementsAnnotatedWith(APIService.class)) {
            mServiceElements.add(element);
        }
        if(mServiceElements.size()==0){
            isGenerate=false;
        }
    }
    private void processOnManager(RoundEnvironment environment){
        for (Element element:environment.getElementsAnnotatedWith(APIManager.class)){
            mManagerElements.add(element);
        }
        if(mManagerElements.size()==0){
            isGenerate=false;
        }
        if(mManagerElements.size()>=2){
            isGenerate=false;
        }
    }

    public static String firstUpperToLetter(String str){
        char[] array = str.toCharArray();
        array[0] += 32;
        return String.valueOf(array);
    }
}
