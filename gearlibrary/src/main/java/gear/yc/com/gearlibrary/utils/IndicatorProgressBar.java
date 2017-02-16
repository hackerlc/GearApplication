package gear.yc.com.gearlibrary.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import gear.yc.com.gearlibrary.R;


/**
 * SJQ_ECSHOP_MJ_NEW
 *
 * xml中使用方式
 * <IndicatorProgressBar
     style="@style/SnatchWholesaleProgressBar"
     android:layout_width="match_parent"
     android:layout_height="10dp"
     android:layout_alignParentLeft="true"
     android:layout_marginLeft="12dp"
     android:layout_marginRight="12dp"
     android:layout_below="@id/v_line"
     android:layout_toLeftOf="@id/btn_enter"
     android:progress="50"
     app:topStartText="$100"
     app:topContentText="$50"
     app:topEndText="$30"
     app:topLightTextSize="20sp"
     app:topLightTextColor="@color/main_color_f82d7c"
     app:topTextSize="16sp"
     app:topTextColor="@color/main_font_color_6"
     app:btmTextColor="@color/main_font_color_6"
     app:btmTextSize="16sp"
     app:btmStartText="起批"
     app:btmUnitText="件"
     app:btmStartNum="20"
     app:btmContentNum="40"
     app:btmEndNum="120"
     app:buyNum="120"/>
 *
 * Created by YichenZ on 2017/2/16 09:27.
 */

public class IndicatorProgressBar extends ProgressBar {
    /**
     * 默认属性
     */
    //indicator default font size
    protected static final int DEF_FONT_SIZE = 16;
    //indicator default text
    protected static final String DEF_TEXT = "";
    //indicator default font color
    protected static final int DEF_FONT_COLOR = 0xff666666;

    //top indicator height
    protected int topHeight;
    //btm indicator height
    protected int btmHeight;
    protected int defHeight;

    /**
     * 变量
     */

    protected int progress_width;
    /**
     * 顶部开始提示器变量
     */
    protected String topStartText;
    /**
     * 中间
     */
    protected String topContentText;
    /**
     * 结尾
     */
    protected String topEndText;

    /**
     * 默认文字尺寸，颜色
     */
    protected int topTextColor;
    protected int topTextSize;
    /**
     * 高亮文字颜色
     */
    protected int topLightTextColor;
    protected int topLightTextSize;

    /**
     * 底部提示器文字颜色，尺寸
     */
    protected int btmTextColor;
    protected int btmTextSize;
    /**
     * 底部提示器开始文字,会加到底部第一个提示器文字最后
     */
    protected String btmStartText;
    /**
     * 底部提示器单位 例如：个 件 元 ect
     */
    protected String btmUnitText;
    /**
     * 底部提示器开始数量
     */
    protected int btmStartNum;
    /**
     * 底部提示器中间数量
     */
    protected int btmContentNum;
    /**
     * 底部提示器结束数量
     */
    protected int btmEndNum;
    /**
     * 当前购买数量
     */
    protected int buyNum;

    /**
     * 绘制
     */

    protected Paint topStartPaint;
    protected Paint topContentPaint;
    protected Paint topEndPaint;


    public IndicatorProgressBar(Context context) {
        this(context, null);
    }

    public IndicatorProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        obtainAttributes(attrs);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        topStartPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        topStartPaint.setAntiAlias(true);
        topStartPaint.setColor(DEF_FONT_COLOR);
        topStartPaint.setTextAlign(Paint.Align.LEFT);
        topStartPaint.setTextSize(DEF_FONT_SIZE);
        topStartPaint.setFakeBoldText(true);

        topContentPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        topContentPaint.setAntiAlias(true);
        topContentPaint.setColor(DEF_FONT_COLOR);
        topContentPaint.setTextAlign(Paint.Align.LEFT);
        topContentPaint.setTextSize(DEF_FONT_SIZE);
        topContentPaint.setFakeBoldText(true);

        topEndPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        topEndPaint.setAntiAlias(true);
        topEndPaint.setColor(DEF_FONT_COLOR);
        topEndPaint.setTextAlign(Paint.Align.RIGHT);
        topEndPaint.setTextSize(DEF_FONT_SIZE);
        topEndPaint.setFakeBoldText(true);
    }

    /**
     * 获取自定义属性
     *
     * @param attrs
     */
    private void obtainAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext()
                .obtainStyledAttributes(attrs, R.styleable.IndicatorProgressBar);
        //顶部控件
        //获取文字
        topStartText = typedArray.getString(R.styleable.IndicatorProgressBar_topStartText);
        topContentText = typedArray.getString(R.styleable.IndicatorProgressBar_topContentText);
        topEndText = typedArray.getString(R.styleable.IndicatorProgressBar_topEndText);
        //获取默认文字尺寸、颜色
        topTextSize = (int) typedArray.getDimension(R.styleable.IndicatorProgressBar_topTextSize,DEF_FONT_SIZE);
        topTextColor = typedArray.getColor(R.styleable.IndicatorProgressBar_topTextColor,DEF_FONT_COLOR);
        //获取高亮文字尺寸、颜色
        topLightTextSize = (int) typedArray.getDimension(R.styleable.IndicatorProgressBar_topLightTextSize,DEF_FONT_SIZE);
        topLightTextColor= typedArray.getColor(R.styleable.IndicatorProgressBar_topLightTextColor,DEF_FONT_COLOR);
        //底部控件
        //文字颜色，尺寸
        btmTextColor =typedArray.getColor(R.styleable.IndicatorProgressBar_btmTextColor,DEF_FONT_COLOR);
        btmTextSize = (int) typedArray.getDimension(R.styleable.IndicatorProgressBar_btmTextSize,DEF_FONT_SIZE);
        //底部提示器开始文字
        btmStartText = typedArray.getString(R.styleable.IndicatorProgressBar_btmStartText);
        //单位
        btmUnitText = typedArray.getString(R.styleable.IndicatorProgressBar_btmUnitText);
        //3数量
        btmStartNum = typedArray.getInteger(R.styleable.IndicatorProgressBar_btmStartNum,0);
        btmContentNum = typedArray.getInteger(R.styleable.IndicatorProgressBar_btmContentNum,0);
        btmEndNum = typedArray.getInteger(R.styleable.IndicatorProgressBar_btmEndNum,0);
        //当前数量
        buyNum = typedArray.getInteger(R.styleable.IndicatorProgressBar_buyNum,0);
        //设置笔触
        setPaintSizeAndColor();

        //根据高亮尺寸设置顶部高度
        Paint paint =new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setColor(topLightTextColor);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(topLightTextSize);
        paint.setFakeBoldText(true);
        topHeight = (int)(paint.getTextSize() + paint.getFontMetrics().bottom);
        //设置底部高度
        paint.setAntiAlias(true);
        paint.setColor(btmTextColor);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(btmTextSize);
        paint.setFakeBoldText(true);
        btmHeight = (int)(paint.getTextSize() + paint.getFontMetrics().bottom);
        paint=null;
        typedArray.recycle();
    }

    /**
     * 根据不同的数量设置笔触颜色尺寸
     */
    private void setPaintSizeAndColor() {
        if(buyNum >=btmEndNum){//全部高亮 80>=120
            topStartPaint.setTextSize(topLightTextSize);
            topStartPaint.setColor(topLightTextColor);

            topContentPaint.setTextSize(topLightTextSize);
            topContentPaint.setColor(topLightTextColor);

            topEndPaint.setTextSize(topLightTextSize);
            topEndPaint.setColor(topLightTextColor);
        }else if(buyNum >= btmContentNum){//中间开始高亮 80>=40
            topStartPaint.setTextSize(topLightTextSize);
            topStartPaint.setColor(topLightTextColor);

            topContentPaint.setTextSize(topLightTextSize);
            topContentPaint.setColor(topLightTextColor);

            topEndPaint.setTextSize(topTextSize);
            topEndPaint.setColor(topTextColor);
        }else if(buyNum >= btmStartNum){//开始高亮
            topStartPaint.setTextSize(topLightTextSize);
            topStartPaint.setColor(topLightTextColor);

            topContentPaint.setTextSize(topTextSize);
            topContentPaint.setColor(topTextColor);

            topEndPaint.setTextSize(topTextSize);
            topEndPaint.setColor(topTextColor);
        }else if(buyNum < btmStartNum){
            topStartPaint.setTextSize(topTextSize);
            topStartPaint.setColor(topTextColor);

            topContentPaint.setTextSize(topTextSize);
            topContentPaint.setColor(topTextColor);

            topEndPaint.setTextSize(topTextSize);
            topEndPaint.setColor(topTextColor);
        }
    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        defHeight=MeasureSpec.getSize(heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(width, height);
        progress_width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    /**
     * 绘制
     *
     * @param canvas
     */
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable != null
                && progressDrawable instanceof LayerDrawable) {
            LayerDrawable d = (LayerDrawable) progressDrawable;

            for (int i = 0; i < d.getNumberOfLayers(); i++) {
                d.getDrawable(i).getBounds().top = topHeight;

                // thanks to Dave [dave@pds-uk.com] for point out a bug
                // which eats up
                // a lot of cpu cycles. It turns out the issue was linked to
                // calling
                // getIntrinsicHeight which proved to be very cpu intensive.
                d.getDrawable(i).getBounds().bottom = topHeight + defHeight;
            }
        } else if (progressDrawable != null) {
            // It's not a layer drawable but we still need to adjust the
            // bounds
            progressDrawable.getBounds().top = topHeight;
            // thanks to Dave[dave@pds-uk.com] -- see note above for
            // explaination.
            progressDrawable.getBounds().bottom = progressDrawable
                    .getBounds().height() + btmHeight;
        }

//        updateProgressBar();

        super.onDraw(canvas);
        canvas.save();
        setTopStartIndicator(canvas, progressDrawable);
        canvas.restore();
    }

    private void updateProgressBar() {
        Drawable progressDrawable = getProgressDrawable();

        if (progressDrawable != null
                && progressDrawable instanceof LayerDrawable) {
            LayerDrawable d = (LayerDrawable) progressDrawable;

            final float scale = getScale(getProgress());

            // get the progress bar and update it's size
            Drawable progressBar = d.findDrawableByLayerId(R.id.progress);

            final int width = d.getBounds().right - d.getBounds().left;

            if (progressBar != null) {
                Rect progressBarBounds = progressBar.getBounds();
                progressBarBounds.right = progressBarBounds.left
                        + (int) (width * scale + 0.5f);
                progressBar.setBounds(progressBarBounds);
            }

        }
    }

    private float getScale(int progress) {
        float scale = getMax() > 0 ? (float) progress / (float) getMax() : 0;

        return scale;
    }

    /**
     * 绘制指示器文字
     *
     * @param canvas
     * @param progressDrawable
     */
    private void setTopStartIndicator(Canvas canvas, Drawable progressDrawable) {
        int dx = 0;

        // get the position of the progress bar's right end
        //        if (progressDrawable != null
        //                && progressDrawable instanceof LayerDrawable) {
        //            LayerDrawable d = (LayerDrawable) progressDrawable;
        //            Drawable progressBar = d.findDrawableByLayerId(R.id.progress);
        //            dx = progressBar.getBounds().right;
        //        } else if (progressDrawable != null) {
        //            dx = progressDrawable.getBounds().right;
        //        }


        canvas.translate(0, 0);
        // indicator start
        float baseline = topHeight / 2 + topStartPaint.getTextSize() / 2 - topStartPaint.getFontMetrics().descent;
        canvas.drawText(progressDrawable
                .getBounds().height()+"", 0, baseline, topStartPaint);
        // indicator content
        float width = progress_width / 2 - (topContentText.length() * topContentPaint.getTextSize()) / 2 +dp2px(10) ;
//        baseline = topHeight / 2 + topContentPaint.getTextSize() / 2 - topStartPaint.getFontMetrics().descent;
        canvas.drawText(topContentText, width, baseline, topContentPaint);
        // indicator end
        width = (progress_width - (topEndText.length() * topEndPaint.getTextSize()) / 2) -dp2px(10);
//        baseline = topHeight / 2 + topEndPaint.getTextSize() / 2 - topStartPaint.getFontMetrics().descent ;
        canvas.drawText(topEndText, width, baseline, topEndPaint);
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        result = specSize + topHeight + btmHeight + getPaddingTop() + getPaddingBottom();
        return result;
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        result = specSize + getPaddingLeft() + getPaddingRight();
        return result;
    }

    protected int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}
