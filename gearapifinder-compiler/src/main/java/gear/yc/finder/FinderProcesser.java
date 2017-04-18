package gear.yc.finder;

import com.gear.apifinder.annotation.APIManager;
import com.gear.apifinder.annotation.APIRouter;
import com.gear.apifinder.annotation.APIService;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import gear.yc.finder.write.APIManagerWrite;
import gear.yc.finder.write.APIRouterWrite;


@AutoService(Processor.class)
public class FinderProcesser extends AbstractProcessor {
    private Filer mFiler;//文件相关
    private Elements mElementUtils;//元素相关
    private Messager mMessager;//日志相关

    APIManagerWrite mAPIManagerWrite;
    APIRouterWrite mAPIRouterWrite;

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
        types.add(APIRouter.class.getCanonicalName());
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
        boolean isGenerate=mAPIManagerWrite.getInstance().init(mElementUtils,roundEnv);

        if(!isGenerate){
            return false;
        }

        try {
            mAPIManagerWrite.getInstance().writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        isGenerate = mAPIRouterWrite.getInstance().init(mElementUtils,roundEnv);
        if(!isGenerate){
            return false;
        }

        try {
            mAPIRouterWrite.getInstance().writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
