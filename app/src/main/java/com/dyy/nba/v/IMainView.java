package com.dyy.nba.v;

import android.view.View;

import com.dyy.nba.model.listdata.MainMenuData;

import java.util.List;

/**
 * Created by 段钰莹 on 2017/9/5.
 */

public interface IMainView extends IView{

    void initLeftMenu(List<MainMenuData> leftMenuData);

    void initViewPager(List<View> pages);
}
