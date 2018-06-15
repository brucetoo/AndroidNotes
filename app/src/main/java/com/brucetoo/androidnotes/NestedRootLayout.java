package com.brucetoo.androidnotes;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by Bruce Too
 * On 14/06/2018.
 * At 18:32
 */
public class NestedRootLayout extends LinearLayout implements NestedScrollingParent {

    public static final String TAG = NestedRootLayout.class.getSimpleName();

    public NestedRootLayout(Context context) {
        super(context);
        init();
    }

    public NestedRootLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NestedRootLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    Scroller scroller;
    int headerHeight;
    View header;
    View tab;
    ViewPager pager;

    private void init() {
        scroller = new Scroller(getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        header = getChildAt(0);
        tab = getChildAt(1);
        headerHeight = header.getMeasuredHeight();
        pager = (ViewPager) getChildAt(2);
        //增大pager的高度
        pager.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(headerHeight + pager.getMeasuredHeight(), MeasureSpec.EXACTLY));
        //整个content高度增加 headerHeight
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() + headerHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        pager.layout(0, tab.getMeasuredHeight() + headerHeight,
                getMeasuredWidth(), getMeasuredHeight() * 2);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        //React to a descendant view initiating a nestable scroll operation
        //响应子view初始化滚动的操作，根据返回值确定是否拦截
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;//只关心垂直方法
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        //此parent成功的拦截子view的滚动事件
        super.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onStopNestedScroll(View child) {
        //拦截事件解释(不再拦截)
        super.onStopNestedScroll(child);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        //只要当onStartNestedScroll返回true后，此方法才会回调，代表ViewParent目前正在消费子view的滚动事件
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        //消费事件之前会回调此方法
        Log.i(TAG, "onNestedPreScroll");

        boolean movingTop = dy > 0 && getScrollY() < headerHeight;
        boolean movingBottom = dy < 0 && getScrollY() > 0 && !canScrollVertically(-1);

        if (movingTop || movingBottom) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        //响应子view的fling操作
        Log.i(TAG, "onNestedFling");

        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        //消费fling操作之前调用
        Log.i(TAG, "onNestedPreFling velocityY:" + velocityY);
        scroller.forceFinished(true);
//        if (velocityY > 0) { //move top
//            scroller.fling(0,getScrollY(), ((int) velocityX), ((int) velocityY),0,0,0,headerHeight - getScrollY());
//        } else { //move down
//            scroller.fling(0,getScrollY(), ((int) velocityX), ((int) velocityY),0,0,-headerHeight + getScrollY(),0);
//        }
        scroller.fling(0,getScrollY(),0, ((int) velocityY),0,0,0,headerHeight);
        return false;//不消费子类的fling操作，但是需要根据速率滚动header
    }

    @Override
    public int getNestedScrollAxes() {
        return super.getNestedScrollAxes();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > headerHeight) {
            y = headerHeight;
        }
        super.scrollTo(x, y);
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(0, scroller.getCurrY());
            invalidate();
        }
    }
}
