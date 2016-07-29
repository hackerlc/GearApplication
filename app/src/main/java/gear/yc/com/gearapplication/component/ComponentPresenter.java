package gear.yc.com.gearapplication.component;

import dagger.Component;
import gear.yc.com.gearapplication.component.medules.AdapterModel;
import gear.yc.com.gearapplication.component.medules.ModulePresenter;
import gear.yc.com.gearapplication.component.scope.ActivityScope;
import gear.yc.com.gearapplication.ui.mvp.travelnotes.TravelNotesActivity;

/**
 * GearApplication
 * Created by YichenZ on 2016/7/22 14:02.
 */
@ActivityScope
@Component(modules = { ModulePresenter.class, AdapterModel.class})
public interface ComponentPresenter {
    void inject(TravelNotesActivity activity);
}
