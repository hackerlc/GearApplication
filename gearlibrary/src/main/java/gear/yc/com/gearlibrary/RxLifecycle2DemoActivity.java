package gear.yc.com.gearlibrary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;

/**
 * 演示类，如果你使用了rxlifecycle2那么就可以复制类的信息到你的BaseActivity中
 * Created by YichenZ on 2016/3/23 11:26.
 */
public class RxLifecycle2DemoActivity extends Activity{
//
//    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
//
//    @NonNull
//    @CheckResult
//    public final Observable<ActivityEvent> lifecycle() {
//        return lifecycleSubject.hide();
//    }
//
//    @NonNull
//    @CheckResult
//    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
//        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
//    }
//
//    @NonNull
//    @CheckResult
//    public final <T> LifecycleTransformer<T> bindToLifecycle() {
//        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
//    }

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        lifecycleSubject.onNext(ActivityEvent.CREATE);
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
//        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
//        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    @CallSuper
    protected void onPause() {
//        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
//        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
//        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }
}
