package gear.yc.com.gearapplication.network.service;

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
@com.gear.apifinder.annotation.APIService
public interface TravelNotesAPI {
    @GET(APIConfig.BASE_URL_TRAVEL_NOTES+"travellist?")
    Flowable<ResponseJson<TravelNoteBook>> getTravelNotesList(@Query("query") String query, @Query("page") String page);
}
