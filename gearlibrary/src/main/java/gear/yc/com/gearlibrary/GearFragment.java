package gear.yc.com.gearlibrary;

import android.app.Fragment;
import android.os.Bundle;

import rx.subscriptions.CompositeSubscription;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/15 16:56.
 */
public class GearFragment extends Fragment{
    //暂时用下面的方式管理一下Rxjava生命周期
    protected CompositeSubscription mCSub;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCSub=new CompositeSubscription();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCSub.unsubscribe();
        mCSub=null;
    }
}
