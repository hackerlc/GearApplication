package gear.yc.com.gearapplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.okhttp.OkHttpClient;

import gear.yc.com.gearlibrary.GearApplication;
import gear.yc.com.gearlibrary.http.OkHttpManager;
import gear.yc.com.gearlibrary.manager.LogManager;

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
        LogManager.getInstance().setDebug(true);
    }
}
