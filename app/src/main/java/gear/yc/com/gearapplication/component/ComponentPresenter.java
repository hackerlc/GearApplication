package gear.yc.com.gearapplication.component;

import dagger.Component;
import gear.yc.com.gearapplication.component.medules.AdapterModel;
import gear.yc.com.gearapplication.component.medules.ModulePresenter;
import gear.yc.com.gearapplication.component.scope.ActivityScope;
import gear.yc.com.gearapplication.ui.mvp.travelnotes.TravelNotesActivity;

/**
 * GearApplication
 * 需要初始化的activity并分配此activity需要的modules，modules类中标示为@Provides的方法可以共用
 * 例如 AdapterModel中需要TravelNotesActivity这个参数，而它就可以使用ModulePresenter中provideActivity
 * 方法获取相关依赖。
 * 都是由Dagger2通过反射自动判断。
 * Created by YichenZ on 2016/7/22 14:02.
 */
@ActivityScope
@Component(modules = { ModulePresenter.class, AdapterModel.class})
public interface ComponentPresenter {
    void inject(TravelNotesActivity activity);
}
