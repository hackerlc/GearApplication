package gear.yc.com.gearapplication;

import gear.yc.com.gearlibrary.GearApplication;
import gear.yc.com.gearlibrary.Manager.LogManager;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/23 11:25.
 */
public class BaseApplication extends GearApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        LogManager.getInstance().setDebug(true);
    }
}
