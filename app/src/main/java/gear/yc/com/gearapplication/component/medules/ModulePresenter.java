package gear.yc.com.gearapplication.component.medules;

import dagger.Module;
import dagger.Provides;
import gear.yc.com.gearapplication.component.scope.ActivityScope;
import gear.yc.com.gearapplication.ui.mvp.travelnotes.TravelNotesActivity;
import gear.yc.com.gearapplication.ui.mvp.travelnotes.TravelNotesContract;
import gear.yc.com.gearapplication.ui.mvp.travelnotes.TravelNotesPresenter;

/**
 * GearApplication
 * Created by YichenZ on 2016/7/22 14:58.
 */
@Module
@ActivityScope
public class ModulePresenter {
    TravelNotesContract.View view;
    TravelNotesActivity obj;

    public ModulePresenter(TravelNotesContract.View view,TravelNotesActivity obj){
        this.view=view;
        this.obj=obj;
    }

    @Provides
    public TravelNotesPresenter provideTravelNotes(){
        return new TravelNotesPresenter(view,obj);
    }

    @Provides
    public TravelNotesActivity provideActivity(){
        return obj;
    }
}
