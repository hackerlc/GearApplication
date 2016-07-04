package gear.yc.com.gearapplication.ui.mvpdemo.travelnotes;

import java.util.ArrayList;

import gear.yc.com.gearapplication.pojo.TravelNoteBook;
import gear.yc.com.gearapplication.ui.mvpdemo.BasePresenter;
import gear.yc.com.gearapplication.ui.mvpdemo.BaseView;

/**
 * GearApplication
 * Created by YichenZ on 2016/6/30 14:59.
 */

public interface TravelNotesContract {

    interface View extends BaseView{
        void changeListView();

        void showDialog();

        void disDialog();

        void setListData(ArrayList<TravelNoteBook.Books> bookies);

        void setListMoreData(ArrayList<TravelNoteBook.Books> bookies);

        void setListMoreView();

        void upListData();
    }

    interface Presenter extends BasePresenter{
        void RefreshData();

        void onResumeData();

        void loadData();
    }

}
