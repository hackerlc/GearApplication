package gear.yc.com.gearapplication.ui.mvpdemo.travelnotes;

import java.util.ArrayList;

import gear.yc.com.gearapplication.network.APIServiceManager;
import gear.yc.com.gearapplication.network.helper.SchedulersHelper;
import gear.yc.com.gearapplication.pojo.TravelNoteBook;
import gear.yc.com.gearlibrary.rxjava.helper.RxSchedulersHelper;
import gear.yc.com.gearlibrary.rxjava.rxbus.RxBus;
import gear.yc.com.gearlibrary.rxjava.rxbus.annotation.Subscribe;
import gear.yc.com.gearlibrary.rxjava.rxbus.event.EventThread;
import gear.yc.com.gearlibrary.utils.ToastUtil;

/**
 * GearApplication
 * Created by YichenZ on 2016/7/4 15:56.
 */

public class TravelNotesPresenter implements TravelNotesContract.Presenter{
    private TravelNotesContract.View view;
    public TravelNotesActivity obj;
    private int page = 1;
    private String query = "";
    private String initQuery = "";

    public TravelNotesPresenter(TravelNotesContract.View view,TravelNotesActivity obj){
        this.view=view;
        this.obj=obj;
    }

    @Override
    public void RefreshData() {
        page=1;
        loadData();
    }

    @Override
    public void onResumeData() {
        if (!initQuery.equals(query) && query != null) {
            initQuery = query;
            page = 1;
            loadData();
        }
    }

    @Override
    public void loadData() {
        view.showDialog();
        APIServiceManager.getInstance()
                .getTravelNotesAPI()
                .getTravelNotesList(query, page + "")
                .compose(obj.bindToLifecycle())
                .compose(RxSchedulersHelper.io_main())
                .compose(SchedulersHelper.handleResult())
                .doOnTerminate(() -> view.disDialog())
                .subscribe(s -> {RxBus.getInstance().post(0, s.getBookses());},
                        e -> {
                            RxBus.getInstance().post(RxBus.TAG_ERROR, e.getMessage());
                        });
    }

    @Subscribe(tag = 0, thread = EventThread.MAIN_THREAD)
    private void dataBinding(ArrayList<TravelNoteBook.Books> bookies) {
        if (page == 1) {
            view.setListData(bookies);
        } else {
            view.setListMoreData(bookies);
        }
        if(bookies.size()==0){
            view.setListMoreView();
        }
        view.upListData();
        page++;
    }

    @Subscribe(tag = RxBus.TAG_ERROR)
    private void dataError(String error) {
        ToastUtil.getInstance().makeShortToast(obj, error);
    }

    @Override
    public void start() {
        RxBus.getInstance().register(this);
    }

    @Override
    public void close() {
        RxBus.getInstance().unRegister(this);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getInitQuery() {
        return initQuery;
    }

    public void setInitQuery(String initQuery) {
        this.initQuery = initQuery;
    }
}
