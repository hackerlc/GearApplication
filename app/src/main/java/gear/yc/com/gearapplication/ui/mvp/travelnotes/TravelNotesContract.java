package gear.yc.com.gearapplication.ui.mvp.travelnotes;

import gear.yc.com.gearapplication.base.BasePresenter;
import gear.yc.com.gearapplication.base.BaseView;

/**
 * GearApplication
 * Created by YichenZ on 2016/6/30 14:59.
 */

public interface TravelNotesContract {

    interface View extends BaseView{
        void changeListView();

        void showDialog();

        void disDialog();
    }

    interface Presenter extends BasePresenter{
        int refreshData(String key,int page,boolean isNote);

        void loadData(String key,int page);

        void loadData(String key,int page,int count);
    }

}
