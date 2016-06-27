package gear.yc.com.gearapplication.manager;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * GearApplication
 * Created by YichenZ on 2016/6/27 14:42.
 */

public class CommonManager {
    private static CommonManager instance;

    public static CommonManager getInstance(@NonNull Application application) {
        if (instance == null) {
            synchronized (CommonManager.class) {
                if (instance == null) {
                    instance = new CommonManager(application);
                }
            }
        }
        return instance;
    }

    public static CommonManager getInstance() {
        return instance;
    }

    private Application mApplication;
    //程序信息
    private PackageInfo packageInfo;

    //系统版本号以及版本名称
    private short currVerCode = 0;
    private String currVerName;

    private int mobileWidth = 0;
    private int mobileHeight = 0;

    public CommonManager(Application application) {
        mApplication = application;
        init();
    }

    private void init() {
        if (currVerCode == 0 && currVerName == null) {
            try {
                packageInfo = mApplication.getPackageManager().getPackageInfo(mApplication.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            currVerCode = (short) packageInfo.versionCode;
            currVerName = packageInfo.packageName;
        }
    }

    public short getCurrVerCode() {
        return currVerCode;
    }

    public String getCurrVerName() {
        return currVerName;
    }

    public int getMobileWidth(Context context) {
        if (mobileWidth == 0) {
            getMobileWindow(context);
        }
        return mobileWidth;
    }

    public int getMobileHeight(Context context) {
        if (mobileHeight == 0) {
            getMobileWindow(context);
        }
        return mobileHeight;
    }

    private void getMobileWindow(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
        mobileWidth = new Integer(dm.widthPixels);
        mobileHeight = new Integer(dm.heightPixels);
        dm=null;
    }
}
