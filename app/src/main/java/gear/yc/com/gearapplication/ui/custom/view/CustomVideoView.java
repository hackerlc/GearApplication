package gear.yc.com.gearapplication.ui.custom.view;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.VideoView;

/**
 * GearApplication
 * Created by YichenZ on 2016/11/15 14:44.
 */

public class CustomVideoView extends VideoView {

    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec),View.MeasureSpec.getSize(heightMeasureSpec));
    }

    public void palyVideo(@NonNull Uri uri){
        setVideoURI(uri);
        start();

        setOnPreparedListener(mp -> mp.setLooping(true));

//        setOnCompletionListener(mp -> mp.start());

        setOnErrorListener((mp, what, extra) -> true);
    }
}
