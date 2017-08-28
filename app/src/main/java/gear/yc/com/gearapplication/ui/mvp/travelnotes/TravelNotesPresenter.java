package gear.yc.com.gearapplication.ui.mvp.travelnotes;

import gear.yc.com.gearapplication.network.APIServiceManager;
import gear.yc.com.gearapplication.network.helper.SchedulersHelper;
import gear.yc.com.gearlibrary.rxjava.helper.RxSchedulersHelper;

/**
 * GearApplication
 * MVP数据逻辑类，承接数据与界面的交互，通过Dagger的方式初始化
 * Created by YichenZ on 2016/7/4 15:56.
 */
public class TravelNotesPresenter implements TravelNotesContract.Presenter {
    private final String INIT_KEY="全国";//面包key不能为空，默认搜索全国后期可以改成定位
    private TravelNotesContract.View view;
    public TravelNotesActivity obj;

    public TravelNotesPresenter(TravelNotesContract.View view, TravelNotesActivity obj) {
        this.view = view;
        this.obj = obj;
    }

    @Override
    public int refreshData(String key,int page,boolean isNote) {
        page = 1;
        if(isNote) {
            loadData(key, page);
        }else{
            loadData(key, page,10);
        }
        return page;
    }

    @Override
    public void loadData(String key,int page) {
        view.showDialog();
//        RouterDemo.getRouter(obj.getLifecycleSubject(),key, page+"")
//                .doOnTerminate(() -> view.disDialog())
//                .subscribe(d -> obj.dataBinding(d),
//                        e -> obj.dataError(e));
        APIServiceManager
                .getTravelNotesAPI()
                .getTravelNotesList(key, page + "")
                .compose(obj.bindToLifecycle())
                .compose(RxSchedulersHelper.io_main())
                .compose(SchedulersHelper.handleResult())
                .doOnTerminate(() -> view.disDialog())
                .subscribe(d -> obj.dataBinding(d),
                        e -> obj.dataError(e));
//                .subscribe(s -> RxBus.getInstance().post(RxBus.getInstance()
//                        .getTag(obj.getClass(),RxBus.TAG_UPDATE), s),
//                        e -> RxBus.getInstance().post(RxBus.getInstance()
//                                .getTag(obj.getClass(),RxBus.TAG_ERROR), e.getMessage()));
    }

    @Override
    public void loadData(String key,int page,int count){
        if("".equals(key) || key==null){
            key=INIT_KEY;
        }
        page=(page-1)*count;
        view.showDialog();
        APIServiceManager
                .getBreadtripAPI()
                .getTravelNotesList(key,String.valueOf(page) ,count+"","trip")
                .compose(obj.bindToLifecycle())
                .compose(RxSchedulersHelper.io_main())
                .compose(SchedulersHelper.handleResultBread())
                .doOnTerminate(() -> view.disDialog())
                .subscribe(d -> obj.dataBinding(d),
                        e -> obj.dataError(e));
//                .subscribe(s -> RxBus.getInstance().post(RxBus.getInstance()
//                                .getTag(obj.getClass(),RxBus.TAG_UPDATE), s.getBookses()),
//                        e -> RxBus.getInstance().post(RxBus.getInstance()
//                                .getTag(obj.getClass(),RxBus.TAG_ERROR), e.getMessage()));
    }

    @Override
    public void start() {
    }

    @Override
    public void close() {
        obj=null;
    }
}
