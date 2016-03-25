package gear.yc.com.gearlibrary.CustomView.carousel;

import android.content.Context;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import gear.yc.com.gearlibrary.ImageLoading.DisplayImg;
import gear.yc.com.gearlibrary.R;
import gear.yc.com.gearlibrary.utils.ConvertPadPlus;

/**
 * BAndroid
 * 轮播图组件
 * Created by YichenZ on 2015/8/10 14:39.
 * @RelyOn CarouseVPAdapter，Volley
 */
public class CarouselRelativeLayout extends RelativeLayout{
    private Context mContext;
    private RelativeLayout mRelativeL;
    //轮播
    private ViewPager viewPage;
    //轮播小点容器
    private LinearLayout layout;
    //图片容器
    private ArrayList<View> views;
    //图片加载容器
    private CarouseVPAdapter pageAdapter;
    //图片URl
    private Object[] url;
    //小点个数
    private int count;
    //如果没有URL是否显示ViewPage
    private boolean isShow=true;
    private int viewOnClickNum=-1;
    ViewOnClickLis mViewOnClickLis;
    //返回点击第几个
    int viewNum=0;
    //移动时间间隔
    int second=0;

    //小点默认样式
    private int focusPot=android.R.drawable.ic_menu_rotate;
    private int unFocusPot=android.R.drawable.ic_menu_rotate;
    private int defaultImg=android.R.drawable.ic_menu_rotate;

    public CarouselRelativeLayout(Context context) {
        this(context, null);
    }

    public CarouselRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.actionLayout);
    }

    public CarouselRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext=context;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mRelativeL= (RelativeLayout)LayoutInflater.from(mContext).inflate(R.layout.carouse_relative_layout, null);
        addView(mRelativeL, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        viewPage=(ViewPager)mRelativeL.findViewById(R.id.viewPage);
        layout=(LinearLayout)mRelativeL.findViewById(R.id.viewGroup);
        layout.removeAllViews();
        views = new ArrayList<View>();
    }

    public void show(Object ... url){
        this.show(false, url);
    }

    //显示图片
    public void show(boolean isStatic, Object ... url){
        this.show(null, isStatic, url);
    }


    /**
     * 数据加载
     * @param pa 传空使用默认adapter
     * @param isStatic 设置是否需要加载网络图片
     * @param url 加载数据所需要的URL
     */
    public void show(PagerAdapter pa,Boolean isStatic,Object ... url){
        this.url=url;

        //设置长度
        count=url.length;
        //数据是否为空
        if(count>0){
            //获取轮播数据
            ImageView[] imageViews=new ImageView[count];
            for (int i = 0; i < count; i++) {
                //设置ImageView
                ImageView view = new ImageView(mContext);
                //设置图片显示方式
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                //设置显示图片大小
                view.setLayoutParams(new LinearLayout.LayoutParams(
                        android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                        android.view.ViewGroup.LayoutParams.MATCH_PARENT));
                if(isStatic){
                    view.setImageResource(Integer.valueOf(url[i].toString()));
                }else{
                    //加载数据动态数据
                    DisplayImg.getInstance().displayImg(mContext,view,url[i].toString());
                }
                views.add(view);

                //加载底部小点，并设置小点形态
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ConvertPadPlus.dip2px(mContext, 10),  ConvertPadPlus.dip2px(mContext,10));
                params.leftMargin = 10;
                ImageView viewPot = new ImageView(mContext);
                viewPot.setLayoutParams(params);
                viewPot.setImageResource(unFocusPot);
                imageViews[i] = viewPot;
                layout.addView(imageViews[i]);
            }
            //初始化第一个小点
            imageViews[0].setImageResource(focusPot);
            viewPage.setOnPageChangeListener(new CarouselViewPager(imageViews));
        }else{
            if(isShow){
                ImageView view = new ImageView(mContext);
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                view.setLayoutParams(new LinearLayout.LayoutParams(
                        android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                        android.view.ViewGroup.LayoutParams.MATCH_PARENT));
                view.setImageResource(defaultImg);
                views.add(view);
            }else{
                mRelativeL.setVisibility(View.GONE);
            }
        }
        setViewLis();
        if(pa==null){
            pageAdapter = new CarouseVPAdapter(views,mContext);
        }
        viewPage.setAdapter(pageAdapter);
        //开启轮播
        setMove();
    }

    /**
     * 设置
     */
    private void setViewLis() {
        if(viewOnClickNum>=0){
            if(viewOnClickNum==0){//全部
                for(View view : views){
                    view.setOnClickListener(mOnClickListener);
                }
            }else{
                views.get(viewOnClickNum-1).setOnClickListener(mOnClickListener);
            }
        }
    }

    OnClickListener mOnClickListener =new OnClickListener() {
        @Override
        public void onClick(View view) {
            mViewOnClickLis.setOnClickLis(viewNum);
        }
    };

    /**
     * 设置无数据显示方式，默认为无数据显示ViewPage
     * @param isShow
     */
    public void isShowView(boolean isShow){
        this.isShow=isShow;
    }

    /**
     * 设置小点样式
     * @param focusPot
     * @param unFocusPot
     * @param defaultImg
     */
    public void setDefaultImage(int focusPot,int unFocusPot,int defaultImg){
        setDefault(focusPot, unFocusPot, defaultImg, 0);
    }
    /**
     * 设置初始数据
     * @param focusPot
     * @param unFocusPot
     * @param defaultImg
     * @param second >0则开启自动轮播
     */
    public void setDefault(int focusPot,int unFocusPot,int defaultImg,int second){
        this.focusPot=focusPot<=0 ? this.focusPot:focusPot;
        this.unFocusPot=unFocusPot<=0 ? this.unFocusPot:unFocusPot;
        this.defaultImg=defaultImg<=0 ? this.defaultImg:defaultImg;
        this.second=second<=0 ? this.second:second;
    }

    /**
     * 设置轮播图第几个轮播图需要点击
     * @param viewOnClickNum -1:不需要;0:全部;>1:第X个
     * @param viewOnClickLis
     */
    public CarouselRelativeLayout setViewOnClickNum(int viewOnClickNum,ViewOnClickLis viewOnClickLis){
        this.viewOnClickNum = viewOnClickNum;
        this.mViewOnClickLis=viewOnClickLis;
        return this;
    }

    ScheduledExecutorService ses;
    public void setMove(){
        if(second>0) {
            if (ses == null) {
                ses = Executors.newSingleThreadScheduledExecutor();
                ses.scheduleAtFixedRate(mRunnable, second, second, TimeUnit.SECONDS);
            } else {
                ses.scheduleAtFixedRate(mRunnable, second, second, TimeUnit.SECONDS);
            }
        }
    }
    public void setStop(){
        if(ses!=null){
            ses.shutdownNow();
        }
    }
    private Runnable mRunnable =new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(1);
        }
    };
    private android.os.Handler mHandler =new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                if(viewPage!=null)
                    viewPage.setCurrentItem(viewPage.getCurrentItem()+1);
            }
        }
    };

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:{
//                setStop();
//                break;
//            }
//            case MotionEvent.ACTION_UP:{
//                setMove();
//                break;
//            }
//        }
//        return super.onInterceptTouchEvent(ev);
//
//    }

    class CarouselViewPager implements ViewPager.OnPageChangeListener{
        ImageView[] imageViews=null;

        public CarouselViewPager(ImageView[] imageViews){
            this.imageViews=imageViews;
        }

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            int length=imageViews.length;
            viewNum=i % length;
            for (int j = 0; j < length; j++) {
                if (viewNum != j) {
                    imageViews[j].setImageResource(unFocusPot);
                } else {
                    imageViews[j].setImageResource(focusPot);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    public interface ViewOnClickLis{
        void setOnClickLis(int i);
    }

}
