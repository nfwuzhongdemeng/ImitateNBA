package com.dyy.nba.d.container;

import com.dyy.nba.d.IInteractor;

/**
 * Created by 段钰莹 on 2017/9/6.
 */

public class TopNewIndicator implements IInteractor {
    private final String[] news = new String[]{
            "今日头条","新闻资讯","视频集锦","最佳进球","赛场花絮"
    };
    public String[] getTabs() {
        return news;
    }
}
