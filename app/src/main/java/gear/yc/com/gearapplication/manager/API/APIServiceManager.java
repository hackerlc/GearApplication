package gear.yc.com.gearapplication.manager.api;

import gear.yc.com.gearapplication.api.service.APIService;
import gear.yc.com.gearlibrary.service.api.GearHttpServiceManager;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/13 14:41.
 */
public class APIServiceManager {
    private static APIServiceManager instance;

    public static APIServiceManager getInstance(){
        if(instance==null){
            synchronized (APIServiceManager.class){
                if(instance==null){
                    instance=new APIServiceManager();
                }
            }
        }
        return instance;
    }

    private APIService apiService;

    public APIServiceManager(){
    }

    public APIService getApiService() {
        return apiService=GearHttpServiceManager.getInstance().getRetrofit().create(APIService.class);
    }
}
