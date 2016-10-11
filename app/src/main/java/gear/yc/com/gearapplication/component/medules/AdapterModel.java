package gear.yc.com.gearapplication.component.medules;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import gear.yc.com.gearapplication.component.scope.ActivityScope;
import gear.yc.com.gearapplication.ui.mvp.travelnotes.TravelNotesActivity;
import gear.yc.com.gearapplication.ui.mvp.travelnotes.TravelNotesAdapter;

/**
 * GearApplication
 * Dagger中相关Adapter所需要使用的model，初始化相关的adapter，可复用
 * @Module 意思为表明此类为Module类
 * @ActivityScope 标示此类的生命周期与activity相同
 * Created by YichenZ on 2016/7/29 17:35.
 */
@Module
@ActivityScope
public class AdapterModel {

    @Provides
    public TravelNotesAdapter provideAdapterData(TravelNotesActivity activity){
        return new TravelNotesAdapter(activity,new ArrayList<>());
    }
}
