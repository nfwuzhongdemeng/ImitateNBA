package com.dyy.nba.p;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.dyy.nba.MainActivity;
import com.dyy.nba.R;
import com.dyy.nba.model.listdata.MainMenuData;
import com.dyy.nba.v.IMainView;
import com.dyy.nba.view.layout.DataLayout;
import com.dyy.nba.view.layout.HupuLayout;
import com.dyy.nba.view.layout.LiveLayout;
import com.dyy.nba.view.layout.OtherLayout;
import com.dyy.nba.view.layout.ScoreLayout;
import com.dyy.nba.view.layout.TopNewLayout;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by 段钰莹 on 2017/10/8.
 */
public class MainPresenterTest implements IMainView {
    private MainPresenter presenter;
    private static final int MAIN_TAB_PAGE = 6;
    @Test
    public void getTitleByPos() throws Exception {
        presenter = new MainPresenter(this);
        presenter.initData();
    }

    @Override
    public void initLeftMenu(List<MainMenuData> leftMenuData) {
        assertEquals(leftMenuData.size(),MAIN_TAB_PAGE);

        checkLeftMenu(leftMenuData.get(0), R.drawable.ic_top_news,"NBA头条");
        checkLeftMenu(leftMenuData.get(1), R.drawable.ic_videos,"赛事直播");
        checkLeftMenu(leftMenuData.get(2), R.drawable.ic_score,"球队战绩");
        checkLeftMenu(leftMenuData.get(3), R.drawable.ic_sort,"数据排行");
        checkLeftMenu(leftMenuData.get(4), R.drawable.ic_hupu,"虎扑专区");
        checkLeftMenu(leftMenuData.get(5), R.drawable.ic_others,"其他");

    }

    private void checkLeftMenu(MainMenuData mainMenuData,int drawableId,String title) {
        assertTrue(mainMenuData.getImgId()==drawableId);
        assertEquals(mainMenuData.getTxt(),title);
    }

    @Override
    public void initViewPager(List<View> pages) {
        assertEquals(pages.size(),MAIN_TAB_PAGE);

        assertTrue(pages.get(0) instanceof TopNewLayout);
        assertTrue(pages.get(1) instanceof LiveLayout);
        assertTrue(pages.get(2) instanceof ScoreLayout);
        assertTrue(pages.get(3) instanceof DataLayout);
        assertTrue(pages.get(4) instanceof HupuLayout);
        assertTrue(pages.get(5) instanceof OtherLayout);
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Override
    public Context getContext() {
        return mActivityRule.getActivity();
    }
}