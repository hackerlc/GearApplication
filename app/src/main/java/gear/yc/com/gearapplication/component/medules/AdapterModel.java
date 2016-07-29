package gear.yc.com.gearapplication.component.medules;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import gear.yc.com.gearapplication.component.scope.ActivityScope;
import gear.yc.com.gearapplication.ui.mvp.travelnotes.TravelNotesActivity;
import gear.yc.com.gearapplication.ui.mvp.travelnotes.TravelNotesAdapter;

/**
 * GearApplication
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
