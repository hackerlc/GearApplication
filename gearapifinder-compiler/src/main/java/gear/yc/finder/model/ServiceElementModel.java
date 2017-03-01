package gear.yc.finder.model;

import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Element;

import gear.yc.finder.utils.StrHandling;

/**
 * GearApplication
 * Created by YichenZ on 2017/1/20 11:37.
 */

public class ServiceElementModel extends BaseModel {
    protected String fieldName;

    public ServiceElementModel(Element element){
        mElement=element;
        mTypeName=TypeName.get(mElement.asType());
        fieldName= element.getSimpleName().toString();
    }
    @Override
    public Element getElement() {
        return mElement;
    }

    @Override
    public TypeName getTypeName() {
        return mTypeName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldName4Letter() {
        return StrHandling.firstUpperToLetter(fieldName);
    }
}
