package gear.yc.com.gearapplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import gear.yc.com.gearapplication.config.APIConfig;
import gear.yc.com.gearlibrary.GearApplication;
import gear.yc.com.gearlibrary.service.http.OkHttpManager;
import gear.yc.com.gearlibrary.manager.LogManager;
import gear.yc.com.gearlibrary.service.api.GearHttpServiceManager;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/23 11:25.
 */
public class BaseApplication extends GearApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        ImagePipelineConfig config =OkHttpImagePipelineConfigFactory
                .newBuilder(this,OkHttpManager.getInstance().getOldClient())
                .build();
        Fresco.initialize(this,config);
        GearHttpServiceManager.getInstance().setBaseUrl(APIConfig.BASE_URL).build();
        LogManager.getInstance().setDebug(true);
    }
}
