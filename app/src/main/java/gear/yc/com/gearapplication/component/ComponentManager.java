package gear.yc.com.gearapplication.component;

import dagger.Component;
import gear.yc.com.gearapplication.component.medules.ModulePojo;
import gear.yc.com.gearapplication.ui.activity.WelcomeActivity;

/**
 * GearApplication
 * Created by YichenZ on 2016/7/22 14:02.
 */
@Component(modules = {ModulePojo.class})
public interface ComponentManager {
    void inject(WelcomeActivity activity);
}
