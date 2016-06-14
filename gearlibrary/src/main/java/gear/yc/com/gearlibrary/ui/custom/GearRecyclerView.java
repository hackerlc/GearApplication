package gear.yc.com.gearlibrary.ui.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/30 17:57.
 */
public class GearRecyclerView extends RecyclerView{

    public GearRecyclerView(Context context) {
        super(context);
    }

    public GearRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GearRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandSpec);
    }
}
