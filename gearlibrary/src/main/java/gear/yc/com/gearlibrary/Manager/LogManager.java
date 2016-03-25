package gear.yc.com.gearlibrary.manager;

import android.util.Log;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/23 14:22.
 */
public class LogManager {
    private static Object obj =new Object();
    private static LogManager instance;

    public static LogManager getInstance(){
        if (instance==null){
            synchronized (obj){
                if (instance==null){
                    instance=new LogManager();
                }
            }
        }
        return instance;
    }

    private static boolean debug=true;
    private static String tag="Log:";

    public LogManager(){}

    public static void logMessage(int str){
        logMessage(String.valueOf(str));
    }

    public static void logMessage(boolean str){
        logMessage(String.valueOf(str));
    }

    public static void logMessage(String str){
        logMessage(debug,str);
    }

    public static void logMessage(boolean flg,String str){
        if(flg) Log.v(tag, str);
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        LogManager.debug = debug;
    }

    public static void setTag(String tag) {
        instance.tag = tag;
    }
}
