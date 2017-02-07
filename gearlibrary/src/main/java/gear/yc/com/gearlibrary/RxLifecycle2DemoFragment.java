package gear.yc.com.gearlibrary;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 演示类，如果你使用了rxlifecycle2那么就可以复制类的信息到你的BaseFragment中
 * Created by YichenZ on 2016/4/15 16:56.
 */
public class RxLifecycle2DemoFragment extends Fragment{

//    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();
//
//    @NonNull
//    @CheckResult
//    public final Observable<FragmentEvent> lifecycle() {
//        return lifecycleSubject.hide();
//    }
//
//    @NonNull
//    @CheckResult
//    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event) {
//        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
//    }
//
//    @NonNull
//    @CheckResult
//    public final <T> LifecycleTransformer<T> bindToLifecycle() {
//        return RxLifecycleAndroid.bindFragment(lifecycleSubject);
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        lifecycleSubject.onNext(FragmentEvent.ATTACH);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        lifecycleSubject.onNext(FragmentEvent.CREATE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
//        lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
//        lifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        super.onPause();
//        lifecycleSubject.onNext(FragmentEvent.PAUSE);
    }

    @Override
    public void onStop() {
//        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
//        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
//        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
//        lifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }
}
