package gear.yc.com.gearlibrary.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/23 11:51.
 */
public class ActivityManager {
    private static ActivityManager instance;

    public static ActivityManager getInstance(){
        if(instance==null){
            synchronized (ActivityManager.class){
                if(instance==null){
                    instance=new ActivityManager();
                }
            }
        }
        return instance;
    }
    public static final String J_FLAG = "FLAG";
    public static final String J_FLAG2 = "FLAG2";


    private static List<Activity> activities;

    public ActivityManager(){
        if(activities==null){
            activities=new ArrayList();
        }
    }

    public static List<Activity> getActivities() {
        return activities;
    }

    public void clearAllActivity(){
        for (Activity activity : activities){
            if(activity!=null){
                activity.finish();
            }
        }
    }

    /**
     * exit app
     */
    public void exitApp() {
        clearAllActivity();
        System.exit(0);
    }
}
