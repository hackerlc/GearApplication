package gear.yc.finder.model;

import com.gear.apifinder.annotation.APIRouter;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;

/**
 * SJQ_ECSHOP_MJ_NEW
 * Created by YichenZ on 2017/3/1 15:31.
 */

public class APIRouterModel extends BaseModel{
    APIRouter mAPIRouter;

    public APIRouterModel(Element element) {
        mElement=element;
        mTypeName=TypeName.get(element.asType());
        mAPIRouter=mElement.getAnnotation(APIRouter.class);
    }

    @Override
    public Element getElement() {
        return mElement;
    }

    @Override
    public TypeName getTypeName() {
        return mTypeName;
    }

    public String getClassName(){
        return mAPIRouter.value();
    }

    public String getrxLifecycleName(){
        return mAPIRouter.rxLifecycle();
    }

    public String getPackageName(Elements elements) {
        return elements.getPackageOf(mElement).getQualifiedName().toString();
    }
}
