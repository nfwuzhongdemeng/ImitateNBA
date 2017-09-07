package com.dyy.nba.view.layout;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.dyy.nba.R;
import com.dyy.nba.commonlibs.view.TabLayout;
import com.dyy.nba.d.container.TopNewIndicator;
import com.dyy.nba.manager.TabViewPagerController;
import com.dyy.nba.p.container.TopNewPresenter;
import com.dyy.nba.v.container.ITopNewsView;

/**
 * Created by 段钰莹 on 2017/9/5.
 * 对应布局：
 * R.layout.page_top_new
 */

public class TopNewLayout extends BaseLinearContainer<ITopNewsView,TopNewIndicator,TopNewPresenter> implements ITopNewsView{
    public TopNewLayout(Context context) {
        super(context);
    }

    public TopNewLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TopNewLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TopNewLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected TopNewPresenter getPresenter() {
        if(presenter == null)
            presenter = new TopNewPresenter(this);
        return presenter;
    }

    @Override
    public void initTabs(String[] tabs) {
        TabLayout tabPage = (TabLayout) getViewById(R.id.top_new_tab);
        ViewPager vp = (ViewPager) getViewById(R.id.top_new_vp);

        TabViewPagerController controller = new TabViewPagerController(tabPage,vp);
        controller.initTab(tabs);
        controller.initViewPager(tabs);
        controller.setListener();
    }

}
