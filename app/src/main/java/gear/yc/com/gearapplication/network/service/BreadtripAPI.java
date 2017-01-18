package gear.yc.com.gearapplication.network.service;

import com.gear.apifinder.annotation.APIService;

import gear.yc.com.gearapplication.config.APIConfig;
import gear.yc.com.gearapplication.pojo.ResponseJson;
import gear.yc.com.gearapplication.pojo.TravelNoteBook;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/20 17:24.
 */
@APIService
public interface BreadtripAPI {
    @GET(APIConfig.BASE_URL_BREADTRIP+"?")
    Flowable<ResponseJson<TravelNoteBook>> getTravelNotesList(@Query("key") String key, @Query("start") String start,
                                                              @Query("count") String count, @Query("data_type") String data_type);
}
