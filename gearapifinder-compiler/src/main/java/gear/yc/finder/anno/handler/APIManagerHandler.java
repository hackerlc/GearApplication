package gear.yc.finder.anno.handler;

import com.gear.apifinder.annotation.APIManager;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;

import gear.yc.finder.model.APIManagerElementModel;

/**
 * GearApplication
 * Created by YichenZ on 2017/1/20 15:44.
 */

public class APIManagerHandler extends AbstractAnnotationHandler<APIManagerElementModel>{

    @Override
    public boolean processorOnAnnotation(RoundEnvironment environment) {
        for (Element element:environment.getElementsAnnotatedWith(APIManager.class)){
            if(element==null){
                return false;
            }
            mEleModel=new APIManagerElementModel(element);
        }
        if(mEleModel==null){
            return false;
        }
        return true;
    }
}
