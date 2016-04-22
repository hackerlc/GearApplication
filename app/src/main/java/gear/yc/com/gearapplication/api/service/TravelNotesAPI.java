package gear.yc.com.gearapplication.api.service;

import gear.yc.com.gearapplication.config.APIConfig;
import gear.yc.com.gearapplication.pojo.ResponseJson;
import gear.yc.com.gearapplication.pojo.TravelNoteBook;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/20 17:24.
 */
public interface TravelNotesAPI {
    @GET(APIConfig.BASE_URL_TRAVEL_NOTES+"travellist?")
    Observable<ResponseJson<TravelNoteBook>> getTravelNotesList(@Query("query") String query, @Query("page") String page);
}
