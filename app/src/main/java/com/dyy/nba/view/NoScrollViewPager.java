package com.dyy.nba.view;

/**
 * Created by Administrator on 2017/6/17.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.dyy.nba.R;


/**
 * 手动控制viewpager是否可以左右滑动
 * 去除滑动动画
 * setNoScroll(boolean noScroll)
 *
 * @author ex-panyong
 */
public class NoScrollViewPager extends ViewPager {
    private boolean enableScroll = false;
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init(attrs);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
            /* return false;//super.onTouchEvent(arg0); */
        return enableScroll && super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return enableScroll && super.onInterceptTouchEvent(arg0);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        //false 去除滚动效果
        super.setCurrentItem(item,enableScroll);
    }

    public void init(AttributeSet attrs){
        TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.NoScrollViewPager);
        enableScroll = t.getBoolean(R.styleable.NoScrollViewPager_canScroll, true);
    }

}
