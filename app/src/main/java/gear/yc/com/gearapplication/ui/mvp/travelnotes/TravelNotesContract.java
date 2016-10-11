package gear.yc.com.gearapplication.ui.mvp.travelnotes;

import gear.yc.com.gearapplication.base.BasePresenter;
import gear.yc.com.gearapplication.base.BaseView;

/**
 * GearApplication
 * MVP接口类
 * Created by YichenZ on 2016/6/30 14:59.
 */

public interface TravelNotesContract {

    /**
     * 界面显示接口
     */
    interface View extends BaseView{
        void changeListView();

        void showDialog();

        void disDialog();
    }

    /**
     * 界面数据接口
     */
    interface Presenter extends BasePresenter{
        int refreshData(String key,int page,boolean isNote);

        void loadData(String key,int page);

        void loadData(String key,int page,int count);
    }

}
