package gear.yc.finder.model;

import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Element;

/**
 * SJQ_ECSHOP_MJ_NEW
 * Created by YichenZ on 2017/3/1 15:32.
 */

public abstract class BaseModel {
    protected Element mElement;
    protected TypeName mTypeName;

    public abstract Element getElement();

    public abstract TypeName getTypeName();
}
