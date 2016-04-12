package gear.yc.com.gearlibrary.ImageLoading;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * BAndroid
 * Created by YichenZ on 2015/8/6 14:57.
 */
public class DisplayImg {
    private static Object obj =new Object();
    private static DisplayImg instance ;

    private static RequestQueue mQueue;
    private static ImageLoader imageLoader;
    private static ImageLoader.ImageListener listener;

    private int noLoading = android.R.drawable.ic_menu_rotate, errLoading = android.R.drawable.ic_delete;

    private DisplayImg() {
    }

    public static DisplayImg getInstance() {
        if(instance==null){
            synchronized (obj){
                if (instance==null){
                    instance=new DisplayImg();
                }
            }
        }
        return instance;
    }

    public DisplayImg setNoLoading(int noLoading) {
        this.noLoading = noLoading;
        return instance;
    }

    public DisplayImg setErrLoading(int errLoading) {
        this.errLoading = errLoading;
        return instance;
    }

    /**
     * 加载图片
     * @param context
     * @param imageView
     * @param url
     */
    public void displayImg(Context context, ImageView imageView, String url) {
        if (mQueue == null)
            mQueue = Volley.newRequestQueue(context);
        if (imageLoader == null)
            imageLoader = new ImageLoader(mQueue, new BitmapCache());
        listener = ImageLoader.getImageListener(imageView, noLoading, errLoading);
        imageLoader.get(url, listener);
    }

    /**
     * 缓存图片
     */
    public static class BitmapCache implements ImageLoader.ImageCache {
        private LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache =new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }

            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }

    }

}
