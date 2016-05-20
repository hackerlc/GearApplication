package gear.yc.com.gearapplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;

import gear.yc.com.gearapplication.config.APIConfig;
import gear.yc.com.gearlibrary.GearApplication;
import gear.yc.com.gearlibrary.manager.LogManager;
import gear.yc.com.gearlibrary.service.api.GearHttpServiceManager;
import gear.yc.com.gearlibrary.service.http.OkHttpManager;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/23 11:25.
 */
public class BaseApplication extends GearApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        GearHttpServiceManager.getInstance()
                .setBaseUrl(APIConfig.BASE_URL)
                .build(
                        OkHttpManager.getInstance()
                                .setHeader("apikey","beae89ef686795322d5a3c48579875d5")
                                .build()
                                .getClient()
                );
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this,OkHttpManager.getInstance().getClient())
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .build();
        Fresco.initialize(this,config);

        LogManager.getInstance().setDebug(true);
    }
}
