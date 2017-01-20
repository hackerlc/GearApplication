package gear.yc.finder.anno.handler;

import com.gear.apifinder.annotation.APIService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;

import gear.yc.finder.model.ServiceElementModel;

/**
 * GearApplication
 * Created by YichenZ on 2017/1/20 17:18.
 */

public class APIServiceHandler extends AbstractAnnotationHandler<List<ServiceElementModel>> {
    @Override
    public boolean processorOnAnnotation(RoundEnvironment environment) {
        mEleModel=new ArrayList<>();
        for (Element element : environment.getElementsAnnotatedWith(APIService.class)) {
            mEleModel.add(new ServiceElementModel(element));
        }
        if(mEleModel.size()==0){
            return false;
        }
        return true;
    }
}
