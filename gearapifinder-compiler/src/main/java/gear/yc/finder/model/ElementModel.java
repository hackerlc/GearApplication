package gear.yc.finder.model;

import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Element;

/**
 * GearApplication
 * Created by YichenZ on 2017/1/20 11:32.
 */

public interface ElementModel {

    Element getElement();

    TypeName getTypeName();
}
