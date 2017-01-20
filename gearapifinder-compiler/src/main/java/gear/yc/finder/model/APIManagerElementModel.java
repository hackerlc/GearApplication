package gear.yc.finder.model;

import com.gear.apifinder.annotation.APIManager;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;

/**
 * GearApplication
 * Created by YichenZ on 2017/1/20 11:52.
 */

public class APIManagerElementModel implements ElementModel {
    protected Element mElement;
    protected TypeName mTypeName;
    protected APIManager annotation;

    public APIManagerElementModel(Element element){
        mElement=element;
        mTypeName=TypeName.get(element.asType());
        annotation=mElement.getAnnotation(APIManager.class);
    }

    @Override
    public Element getElement() {
        return mElement;
    }

    @Override
    public TypeName getTypeName() {
        return mTypeName;
    }

    public APIManager getAnnotation() {
        return annotation;
    }

    public String getClassName() {
        return annotation.value();
    }

    public String getPackageName(Elements elements) {
        return elements.getPackageOf(mElement).getQualifiedName().toString();
    }
}
