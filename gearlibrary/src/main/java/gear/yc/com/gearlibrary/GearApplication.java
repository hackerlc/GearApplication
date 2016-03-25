package gear.yc.com.gearlibrary;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/23 11:24.
 */
public class GearApplication extends Application{

    //系统版本号以及版本名称
    public short currVerCode=0;
    public String currVerName;
    //程序信息
    private PackageInfo packageInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        //设置版本名以及版本号
        if(currVerCode==0&&currVerName==null){
            try {
                packageInfo=getPackageManager().getPackageInfo(getPackageName(),0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            currVerCode=(short)packageInfo.versionCode;
            currVerName=packageInfo.packageName;
        }
    }
}
