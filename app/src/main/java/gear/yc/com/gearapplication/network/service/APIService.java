package gear.yc.com.gearapplication.network.service;

import gear.yc.com.gearapplication.pojo.ResponseJson;
import gear.yc.com.gearapplication.pojo.User;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/13 14:37.
 */
public interface APIService {
    @GET("src/app/{path}")
    Observable<ResponseJson<User>> getUser(@Path("path") String path);

//    @POST("src/app/demo.php")
//    Call<ResponseJson<User>> getUser();

    @POST("src/app/demo.php")
    Observable<ResponseJson<User>> getUser();
}
