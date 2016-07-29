package gear.yc.com.gearapplication.component.medules;

import dagger.Module;
import dagger.Provides;
import gear.yc.com.gearapplication.pojo.Clock;

/**
 * GearApplication
 * Created by YichenZ on 2016/7/22 14:31.
 */
@Module
public class ModulePojo {

    @Provides
    public Clock provideClock(){
        return new Clock();
    }
}
