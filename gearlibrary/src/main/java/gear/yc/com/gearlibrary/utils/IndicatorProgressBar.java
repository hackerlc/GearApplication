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
 * <com.ecjia.widgets.IndicatorProgressBar
 style="@style/TogetherWholesaleProgressBar"
 android:layout_width="match_parent"
 android:layout_height="6dp"
 android:layout_alignParentLeft="true"
 android:layout_marginLeft="12dp"
 android:layout_marginRight="12dp"
 android:layout_below="@id/v_line"
 android:layout_toLeftOf="@id/btn_enter"
 android:layout_marginTop="12dp"
 android:progress="50"
 app:topStartText="$100"
 app:topContentText="$50"
 app:topEndText="$30"
 app:topLightTextSize="20sp"
 app:topLightTextColor="@color/main_color_f82d7c"
 app:topTextSize="16sp"
 app:topTextColor="@color/main_font_color_6"
 app:btmTextColor="@color/main_font_color_9"
 app:btmTextSize="16sp"
 app:btmStartText="起批"
 app:btmUnitText="件"
 app:btmStartNum="20"
 app:btmContentNum="40"
 app:btmEndNum="120"
 app:buyNum="100"
 app:pointColor="@color/main_font_color_e7e7e7"
 app:pointLightColor="@color/main_color_db0f61"/>
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
     * 圆点默认颜色和高亮颜色
     */
    protected int pointColor;
    protected int pointLightColor;

    /**
     * 绘制
     */

    protected Paint topStartPaint;
    protected Paint topContentPaint;
    protected Paint topEndPaint;
    protected Paint btmPaint;
    //三个圆点的笔触
    protected Paint contentStartPaint;
    protected Paint contentPaint;
    protected Paint contentEndPaint;


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
        setProgress();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        topStartPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        topStartPaint.setColor(DEF_FONT_COLOR);
        topStartPaint.setTextAlign(Paint.Align.LEFT);
        topStartPaint.setTextSize(DEF_FONT_SIZE);
        topStartPaint.setFakeBoldText(true);

        topContentPaint = new TextPaint(topStartPaint);

        topEndPaint = new TextPaint(topStartPaint);
        topEndPaint.setTextAlign(Paint.Align.CENTER);

        btmPaint = new TextPaint(topStartPaint);

        contentStartPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        contentStartPaint.setAntiAlias(true);
        contentStartPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        contentPaint=new Paint(contentStartPaint);
        contentEndPaint=new Paint(contentStartPaint);
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

        //圆点默认颜色和高亮颜色
        pointColor = typedArray.getColor(R.styleable.IndicatorProgressBar_pointColor,DEF_FONT_COLOR);
        pointLightColor = typedArray.getColor(R.styleable.IndicatorProgressBar_pointLightColor,DEF_FONT_COLOR);

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
        btmPaint.setTextSize(btmTextSize);
        btmPaint.setColor(btmTextColor);

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
        btmHeight = (int)(paint.getTextSize() + paint.getFontMetrics().bottom * 2);
        paint=null;
        typedArray.recycle();
    }

    /**
     * 根据不同的数量设置笔触颜色尺寸
     */
    private void setPaintSizeAndColor() {
        if(buyNum >=btmEndNum){//全部高亮
            topStartPaint.setTextSize(topLightTextSize);
            topStartPaint.setColor(topLightTextColor);

            topContentPaint.setTextSize(topLightTextSize);
            topContentPaint.setColor(topLightTextColor);

            topEndPaint.setTextSize(topLightTextSize);
            topEndPaint.setColor(topLightTextColor);

            contentStartPaint.setColor(pointLightColor);
            contentPaint.setColor(pointLightColor);
            contentEndPaint.setColor(pointLightColor);
        }else if(buyNum >= btmContentNum){//中间开始高亮
            topStartPaint.setTextSize(topLightTextSize);
            topStartPaint.setColor(topLightTextColor);

            topContentPaint.setTextSize(topLightTextSize);
            topContentPaint.setColor(topLightTextColor);

            topEndPaint.setTextSize(topTextSize);
            topEndPaint.setColor(topTextColor);

            contentStartPaint.setColor(pointLightColor);
            contentPaint.setColor(pointLightColor);
            contentEndPaint.setColor(pointColor);
        }else if(buyNum >= btmStartNum){//开始高亮
            topStartPaint.setTextSize(topLightTextSize);
            topStartPaint.setColor(topLightTextColor);

            topContentPaint.setTextSize(topTextSize);
            topContentPaint.setColor(topTextColor);

            topEndPaint.setTextSize(topTextSize);
            topEndPaint.setColor(topTextColor);

            contentStartPaint.setColor(pointLightColor);
            contentPaint.setColor(pointColor);
            contentEndPaint.setColor(pointColor);
        }else{
            topStartPaint.setTextSize(topTextSize);
            topStartPaint.setColor(topTextColor);

            topContentPaint.setTextSize(topTextSize);
            topContentPaint.setColor(topTextColor);

            topEndPaint.setTextSize(topTextSize);
            topEndPaint.setColor(topTextColor);

            contentStartPaint.setColor(pointColor);
            contentPaint.setColor(pointColor);
            contentEndPaint.setColor(pointColor);
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
        setPaintSizeAndColor();//设置一下笔触，不知道是否会影响到16ms
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable != null
                && progressDrawable instanceof LayerDrawable) {
            LayerDrawable d = (LayerDrawable) progressDrawable;

            for (int i = 0; i < d.getNumberOfLayers(); i++) {
                d.getDrawable(i).getBounds().left = defHeight / 2;
                d.getDrawable(i).getBounds().top = topHeight;
                d.getDrawable(i).getBounds().right = progress_width - defHeight / 2;
                d.getDrawable(i).getBounds().bottom = topHeight + defHeight;
            }
        }

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
        canvas.translate(0, 0);
        // indicator start
        float baseline = topHeight / 2 + topStartPaint.getTextSize() / 2 - topStartPaint.getFontMetrics().descent;
        canvas.drawText(topStartText, 0, baseline, topStartPaint);
        // indicator content
        float width = progress_width / 2 - (topContentText.length() * topContentPaint.getTextSize()) / 2 +dp2px(10);
        canvas.drawText(topContentText, width, baseline, topContentPaint);
        // indicator end
        width = (progress_width - (topEndText.length() * topEndPaint.getTextSize()) /2);
        canvas.drawText(topEndText, width, baseline, topEndPaint);

        //绘制圆点
        int x = 0;
        int y = topHeight + defHeight / 2;
        int r = defHeight - defHeight / 4;
        //1
        x = x +defHeight ;
        canvas.drawCircle(x,y,r,contentStartPaint);
        //2
        x = progress_width / 2;
        canvas.drawCircle(x,y,r,contentPaint);
        //3
        x = progress_width - defHeight;
        canvas.drawCircle(x,y,r,contentEndPaint);
        //绘制下方提示器
        canvas.translate(0 , topHeight + defHeight);
        //1
        canvas.drawText(btmStartNum+btmUnitText+btmStartText, 0, baseline,btmPaint);
        //2
        width = progress_width / 2 - (topContentText.length() * topContentPaint.getTextSize()) / 2 +dp2px(10) ;
        canvas.drawText(btmContentNum+btmUnitText, width, baseline,btmPaint);
        //3
        TextPaint paint =new TextPaint(btmPaint);
        paint.setTextAlign(Paint.Align.CENTER);
        width = (progress_width - (topEndText.length() * topEndPaint.getTextSize()) / 2);
        canvas.drawText(btmEndNum+btmUnitText, width, baseline,paint);
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

    /**
     * 根据中间值，最大值，当前值设置进度条
     * 因为中间值不一定会是最大值的一半
     * 但是如果当前值等于中间值，那么进度条应该设置为50%
     * 那么50%以下的数值应该增加偏移量，超过中间值后则正常显示
     * 计算偏移量
     * 判断当前值是否超过中间值，如果没有超过那么当前值+中间值显示取百分比显示
     * 进度条为0-100
     * 20 40 120  buy20   40  80  计算百分比
     * 60
     * offset 20
     */
    int max =100;
    public synchronized void setProgress(){
        int progress = 0;
        setMax(btmEndNum);
        int offset = 0;
        offset = (btmEndNum / 2) - btmContentNum;
        if(buyNum <= btmContentNum){
            progress=buyNum+offset;
        }else{
            progress = buyNum;
        }
        setProgress(progress);
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
    }

    /**
     * 锁定最大值
     * @param max
     */
    @Override
    public synchronized void setMax(int max) {
        max =this.max;
        super.setMax(max);
    }

    public String getTopStartText() {
        return topStartText;
    }

    public void setTopStartText(String topStartText) {
        this.topStartText = topStartText;
    }

    public String getTopContentText() {
        return topContentText;
    }

    public void setTopContentText(String topContentText) {
        this.topContentText = topContentText;
    }

    public String getTopEndText() {
        return topEndText;
    }

    public void setTopEndText(String topEndText) {
        this.topEndText = topEndText;
    }

    public int getTopTextColor() {
        return topTextColor;
    }

    public void setTopTextColor(int topTextColor) {
        this.topTextColor = topTextColor;
    }

    public int getTopTextSize() {
        return topTextSize;
    }

    public void setTopTextSize(int topTextSize) {
        this.topTextSize = topTextSize;
    }

    public int getTopLightTextColor() {
        return topLightTextColor;
    }

    public void setTopLightTextColor(int topLightTextColor) {
        this.topLightTextColor = topLightTextColor;
    }

    public int getTopLightTextSize() {
        return topLightTextSize;
    }

    public void setTopLightTextSize(int topLightTextSize) {
        this.topLightTextSize = topLightTextSize;
    }

    public int getBtmTextColor() {
        return btmTextColor;
    }

    public void setBtmTextColor(int btmTextColor) {
        this.btmTextColor = btmTextColor;
    }

    public int getBtmTextSize() {
        return btmTextSize;
    }

    public void setBtmTextSize(int btmTextSize) {
        this.btmTextSize = btmTextSize;
    }

    public String getBtmStartText() {
        return btmStartText;
    }

    public void setBtmStartText(String btmStartText) {
        this.btmStartText = btmStartText;
    }

    public String getBtmUnitText() {
        return btmUnitText;
    }

    public void setBtmUnitText(String btmUnitText) {
        this.btmUnitText = btmUnitText;
    }

    public int getBtmStartNum() {
        return btmStartNum;
    }

    public void setBtmStartNum(int btmStartNum) {
        this.btmStartNum = btmStartNum;
    }

    public int getBtmContentNum() {
        return btmContentNum;
    }

    public void setBtmContentNum(int btmContentNum) {
        this.btmContentNum = btmContentNum;
    }

    public int getBtmEndNum() {
        return btmEndNum;
    }

    public void setBtmEndNum(int btmEndNum) {
        this.btmEndNum = btmEndNum;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public int getPointColor() {
        return pointColor;
    }

    public void setPointColor(int pointColor) {
        this.pointColor = pointColor;
    }

    public int getPointLightColor() {
        return pointLightColor;
    }

    public void setPointLightColor(int pointLightColor) {
        this.pointLightColor = pointLightColor;
    }
}
