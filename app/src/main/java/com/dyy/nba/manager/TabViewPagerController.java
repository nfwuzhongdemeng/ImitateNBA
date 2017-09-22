package com.dyy.nba.manager;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.dyy.nba.R;
import com.dyy.nba.adapter.ViewPagerAdapter;
import com.dyy.nba.commonlibs.view.TabLayout;
import com.dyy.nba.view.layout.subtopnew.TopNewListLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 段钰莹 on 2017/9/6.
 */

public class TabViewPagerController {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public TabViewPagerController(TabLayout tabLayout, ViewPager viewPager) {
        this.tabLayout = tabLayout;
        this.viewPager = viewPager;
    }

    public void initTab(String[] names) {
        tabLayout.addTabs(names, 40, 20, 18);
        viewPager.setOffscreenPageLimit(names.length);
    }

    private int nowPos = 0;

    public void initViewPager(String[] tabs) {
        List<View> views = new ArrayList<>();

        for (int i = 0; i < tabs.length; i++) {
            TopNewListLayout listLayout = (TopNewListLayout) LayoutInflater.from(viewPager.getContext()).inflate(R.layout.page_top_new_sub_list, null);
            listLayout.setTag(i);
            views.add(listLayout);
        }

        ViewPagerAdapter adapter = new ViewPagerAdapter(views);
        viewPager.setAdapter(adapter);
    }

    private boolean fromTab = false;
    private boolean fromVp = false;
    private String TAG = "TabViewPagerControlller";
    public void setListener() {
        tabLayout.setOnSelectTabListener(new TabLayout.SelectTabListener() {
            @Override
            public void onSelect(int position) {
                if (position == nowPos)
                    return;
                nowPos = position;
                if (fromVp) {
                    fromVp = false;
                    return;
                }

                fromTab = true;
                viewPager.setCurrentItem(position);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.i(TAG,"position="+position);
//                Log.i(TAG,"positionOffset="+positionOffset);
                if (fromTab) {
                    return;
                }

                tabLayout.followPos(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                if (fromTab) {
                    fromTab = false;
                    return;
                }
                fromVp = true;
                tabLayout.setTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
