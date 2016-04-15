package gear.yc.com.gearlibrary.utils;

import android.content.Context;

/**
 * BAndroid
 * Created by YichenZ on 2015/8/10 14:18.
 * 单位转换 dp-px px-dp
 */
public class ConvertPadPlus {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param context this
     * @param dpValue dp value
     * @return px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     * @param context this
     * @param pxValue px value
     * @return dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
