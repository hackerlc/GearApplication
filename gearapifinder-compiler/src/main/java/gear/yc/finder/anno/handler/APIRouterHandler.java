package gear.yc.finder.anno.handler;

import com.gear.apifinder.annotation.APIRouter;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;

import gear.yc.finder.model.APIRouterModel;

/**
 * SJQ_ECSHOP_MJ_NEW
 * Created by YichenZ on 2017/3/1 15:28.
 */

public class APIRouterHandler extends AbstractAnnotationHandler<APIRouterModel> {

    @Override
    public boolean processorOnAnnotation(RoundEnvironment environment) {
        for(Element element : environment.getElementsAnnotatedWith(APIRouter.class)){
            if(element==null) {
                return false;
            }
            mEleModel = new APIRouterModel(element);
        }
        if(mEleModel == null){
            return false;
        }
        return true;
    }
}
