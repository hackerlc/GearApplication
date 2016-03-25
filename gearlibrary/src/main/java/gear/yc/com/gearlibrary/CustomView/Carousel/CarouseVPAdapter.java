package gear.yc.com.gearlibrary.CustomView.Carousel;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

/**
 * BAndroid
 * Created by YichenZ on 2015/8/5 15:49.
 */
public class CarouseVPAdapter extends PagerAdapter{
    private ArrayList<View> views;
    private Context context;

    public CarouseVPAdapter(ArrayList<View> views, Context context){
        this.context=context;
        this.views=views;
    }

    public void setViews(ArrayList<View> views){
        this.views=views;
    }

    public void addViews(ArrayList<View> views){
        this.views.addAll(views);
    }

    public ArrayList<View> getViews(){
        return views;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(views.get(arg1%views.size()));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void finishUpdate(View arg0) {
        super.finishUpdate(arg0);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        final int pos = position % views.size();
        View view =views.get(pos);
        ((ViewPager)container).addView(view,0);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view==o;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}
