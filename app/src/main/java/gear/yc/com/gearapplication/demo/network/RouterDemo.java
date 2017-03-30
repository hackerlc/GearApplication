package gear.yc.com.gearapplication.demo.network;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;

import gear.yc.com.gearapplication.network.APIServiceManager;
import gear.yc.com.gearapplication.network.helper.SchedulersHelper;
import gear.yc.com.gearapplication.pojo.TravelNoteBook;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * GearApplication
 * Created by YichenZ on 2017/3/30 13:45.
 */

public class RouterDemo {

    public static final Flowable<TravelNoteBook> getRouter(BehaviorSubject<ActivityEvent>lifecycleSubject, String query, String page){
        return APIServiceManager
                .getTravelNotesAPI()
                .getTravelNotesList(query, page)
                .compose(bindUntilEvent(lifecycleSubject))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(SchedulersHelper.handleResult());
    }

    public static final <T> LifecycleTransformer<T> bindUntilEvent(BehaviorSubject<ActivityEvent> lifecycleSubject) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, ActivityEvent.DESTROY);
    }

}
