package com.dyy.nba.v.container;

import com.dyy.nba.http.enumate.NewsType;
import com.dyy.nba.model.NewsItemBean;
import com.dyy.nba.v.IView;

import java.util.List;

/**
 * Created by 段钰莹 on 2017/9/6.
 */

public interface ITopNewBaseView extends IView {
    void initView();

    NewsType getNewsType();

    List<NewsItemBean> getNewsList();

    void refresh();
}
