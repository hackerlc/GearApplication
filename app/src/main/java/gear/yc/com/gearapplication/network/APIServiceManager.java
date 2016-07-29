package gear.yc.com.gearapplication.network;

import gear.yc.com.gearapplication.network.service.APIService;
import gear.yc.com.gearapplication.network.service.BreadtripAPI;
import gear.yc.com.gearapplication.network.service.TravelNotesAPI;
import gear.yc.com.gearlibrary.network.api.GearHttpServiceManager;

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

    public APIServiceManager(){
    }

    private APIService apiService;
    public APIService getApiService() {
        return apiService==null ?
        GearHttpServiceManager.getInstance().getRetrofit().create(APIService.class)
        :apiService;
    }

    private TravelNotesAPI mTravelNotesAPI;
    public TravelNotesAPI getTravelNotesAPI(){
        return mTravelNotesAPI==null ?
                GearHttpServiceManager.getInstance().getRetrofit().create(TravelNotesAPI.class)
                : mTravelNotesAPI;
    }

    private BreadtripAPI mBreadtripAPI;
    public BreadtripAPI getBreadtripAPI(){
        return mBreadtripAPI==null ?
                GearHttpServiceManager.getInstance().getRetrofit().create(BreadtripAPI.class)
                : mBreadtripAPI;
    }


}
