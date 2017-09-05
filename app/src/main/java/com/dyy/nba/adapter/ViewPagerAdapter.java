package com.dyy.nba.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 段钰莹 on 2017/9/5.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<View> views;

    public ViewPagerAdapter(List<View> views){
        this.views = views;
    }
    @Override
    public int getCount() {
        return views == null ? 0: views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        //每次滑动的时候把视图添加到viewpager
        return views.get(position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        // 将当前位置的View移除
        container.removeView(views.get(position));
    }
}
